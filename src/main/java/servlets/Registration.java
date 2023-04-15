package servlets;

import database.GetConnection;
import database.dao.TeamDAO;
import database.dao.TeamMemberDAO;
import database.entity.Team;
import database.entity.TeamMember;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class Registration extends HttpServlet {


    TeamMemberDAO teamMemberDAO = new TeamMemberDAO();
    TeamDAO teamDAO = new TeamDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String secret = req.getParameter("secret");
        String team = req.getParameter("team");
        Properties properties = GetConnection.getProperties();
        Team team_obj = new Team();
        team_obj.setDone(0);
        team_obj.setEmail(email);
        team_obj.setName(team);
        team_obj.setEnable(false);
        team_obj.setPoints(Integer.parseInt(properties.getProperty("points")));
        team_obj.setSecret(secret);
        TeamMember member = new TeamMember();
        member.setName(username);
        member.setTeam(team);
        try {
            Team exists = teamDAO.get(team);
            if (exists != null ){
                resp.sendRedirect(req.getServletContext().getContextPath()+"/registration.jsp?message=Team Already Exists");
                return;
            }
            teamDAO.insert(team_obj);
            teamMemberDAO.insert(member);
            resp.sendRedirect(req.getServletContext().getContextPath()+"/home.jsp?message=Registered Successfully");
        }
        catch (Exception e){
            resp.sendRedirect(req.getServletContext().getContextPath()+"/registration.jsp?message=There is some problem");
        }

    }
}
