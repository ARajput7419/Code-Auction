package filters;


import database.dao.AssignedDAO;
import database.dao.TeamDAO;
import database.entity.Assigned;
import database.entity.Team;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Login implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    TeamDAO teamDAO = new TeamDAO();
    AssignedDAO assignedDAO = new AssignedDAO();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute("team")==null){

            String team = request.getParameter("team");
            String secret = request.getParameter("secret");

            if( team == null || secret == null){

                response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
                return;

            }

            try {
                Team team_obj = teamDAO.get(team);
                if (team_obj != null) {
                    List<Assigned> questions = assignedDAO.allAssigned(team);
                    if (team_obj.getSecret().equals(secret) && team_obj.isEnable()){
                        session.setMaxInactiveInterval(7200);
                        session.setAttribute("team",team_obj);
                        session.setAttribute("questions",questions);
                        filterChain.doFilter(request,response);
                        return;
                    }
                    else{
                        response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
                        return;
                    }
                }

            }
            catch (Exception e){

            }
            response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp?message=Login Failed");
            return;
        }
        else
        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
