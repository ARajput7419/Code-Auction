package servlets;

import database.dao.AssignedDAO;
import database.dao.QuestionDAO;
import database.dao.TeamDAO;
import database.entity.Assigned;
import database.entity.Question;
import database.entity.Team;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Bid extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("admin")==null){
            response.sendRedirect(request.getServletContext().getContextPath()+"/admin_login.jsp");
            return;
        }
        String team = request.getParameter("team");
        int id = Integer.parseInt(request.getParameter("id"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        TeamDAO teamDAO = new TeamDAO();
        QuestionDAO questionDAO = new QuestionDAO();
        try {
            Team team_obj = teamDAO.get(team);
            Question question = questionDAO.get(id);
            if (question != null && team_obj!=null && team_obj.getPoints()>=amount) {
                AssignedDAO assignedDAO = new AssignedDAO();
                Assigned assigned = new Assigned();
                assigned.setTeam(team);
                assigned.setId(id);
                assignedDAO.insert(assigned);
                team_obj.setPoints(team_obj.getPoints()-amount);
                team_obj.setDone(team_obj.getDone()+1);
                teamDAO.update(team_obj);
                response.sendRedirect(getServletContext().getContextPath()+"/dashboard?message=Success");
            }
            else{
                throw  new Exception("Null ...");
            }
        }
        catch (Exception exception){
            response.sendRedirect(request.getServletContext().getContextPath()+"/dashboard?message="+exception.toString());
        }

    }
}
