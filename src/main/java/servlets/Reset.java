package servlets;

import database.GetConnection;
import database.dao.TeamDAO;
import database.entity.Team;
import email.EmailService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class Reset extends HttpServlet {


    TeamDAO teamDAO = new TeamDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        Properties properties = GetConnection.getProperties();
        String message = null;
        try {
            Team team = teamDAO.getByEmail(email);
            String secret = team.getSecret();
            if (email != null) {
                String subject = "Secret Code";
                String body = "Your Secret Code is : <strong>"+secret+"</strong><br><br>Sent By :<br>Code Auction Team";
                EmailService.sendMail(properties.getProperty("email_username"), email, subject,body);
                message = "Secret Code is sent Successfully!!!";

            }
            else
                message = "Failed";
        }
        catch (Exception exception){
            message = "Failed !!";
        }

        resp.sendRedirect(req.getServletContext().getContextPath()+"/login?message="+message);

    }
}
