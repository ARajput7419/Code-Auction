package servlets;

import database.dao.TeamDAO;
import database.dao.TeamMemberDAO;
import database.entity.Team;
import database.entity.TeamMember;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Join extends HttpServlet {

    TeamDAO dao = new TeamDAO();
    TeamMemberDAO memberDAO = new TeamMemberDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String secret = request.getParameter("secret");
        String team = request.getParameter("team");
        try {
            Team team_obj = dao.get(team);
            if (team_obj != null && team_obj.getSecret().equals(secret)){
                TeamMember member = new TeamMember();
                member.setTeam(team);
                member.setName(username);
                memberDAO.insert(member);
                response.sendRedirect(request.getServletContext().getContextPath()+"/home.jsp?message=Joined Successfully");
                return;
            }
            else{
                throw new Exception(team_obj == null ? "Team not present ":"Secret Mismatched");
            }
        }
        catch (Exception e ){
            response.sendRedirect(request.getServletContext().getContextPath()+"/join.jsp?message="+e.toString());
        }

    }
}
