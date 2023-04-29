package filters;


import database.dao.AssignedDAO;
import database.dao.QuestionDAO;
import database.entity.Assigned;
import database.entity.Question;
import database.entity.Team;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionCollector implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    QuestionDAO questionDAO = new QuestionDAO();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Team team = (Team) session.getAttribute("team");
        List<Assigned> questions = (List<Assigned>) session.getAttribute("questions");

        if (questions.size() == 0 ){
            response.sendRedirect(request.getServletContext().getContextPath()+"/status.jsp");
            return;
        }

        try {
            String temp = request.getParameter("number");
            int problem_number = temp == null ? 1 : Integer.parseInt(temp);
            int total = questions.size();
            problem_number = problem_number <= 0 ? total : (problem_number > total ? 1 : problem_number);
            Assigned assigned = questions.get(problem_number - 1);
            Question question = questionDAO.get(assigned.getId());
            int total_solved = 0;
            for(Assigned assigned1 : questions) if(assigned1.getFilename()!=null) total_solved++;
            request.setAttribute("statement",question.getStatement().replace("\n","<br>"));
            request.setAttribute("total",questions.size());
            request.setAttribute("solved",total_solved);
            request.setAttribute("current",problem_number);
            request.setAttribute("number",question.getId());
            request.setAttribute("team",team.getName());
            request.setAttribute("level",question.getLevel());
            request.setAttribute("isSolved",assigned.getFilename()!=null);
            filterChain.doFilter(request,response);
        }
        catch(Exception exception){
            request.setAttribute("message","Question Loading Failed !!!");
            filterChain.doFilter(request,response);
        }


    }

    @Override
    public void destroy() {

    }
}
