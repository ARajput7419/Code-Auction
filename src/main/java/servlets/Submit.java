package servlets;

import database.GetConnection;
import database.dao.AssignedDAO;
import database.entity.Assigned;
import database.entity.Team;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class Submit extends HttpServlet {

    private boolean upload(InputStream stream , String name)  {
        Properties properties = GetConnection.getProperties();
        String directory = properties.getProperty("destination");
        String root = getServletContext().getRealPath("/");
        try {
            FileOutputStream outputStream = new FileOutputStream(root+"/"+directory+"/"+name);
            outputStream.write(stream.readAllBytes());
            outputStream.close();
            return true;
        }
        catch (Exception exception){
            return false;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("team") == null){

            response.sendRedirect(request.getServletContext().getContextPath()+"/login");
            return;

        }

        Team team = (Team) session.getAttribute("team");
        List<Assigned> questions = (List<Assigned>) session.getAttribute("questions");
        int number = Integer.parseInt(request.getParameter("number"));
        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
            try {
                List<FileItem> items = fileUpload.parseRequest(request);
                FileItem item =  items.get(0);
                InputStream stream = item.getInputStream();
                String components []  = item.getName().split("\\.");
                String extension = components[components.length-1];
                String filename = team.getName()+"_ques_id_"+questions.get(number-1).getId()+"."+extension;
                boolean status = upload(stream,filename);
                if (status){

                    Assigned question  = questions.get(number-1);
                    AssignedDAO dao = new AssignedDAO();
                    question.setFilename(filename);
                    dao.update(question);

                }
                response.sendRedirect(getServletContext().getContextPath()+"/problems?number="+number+"&message=Submitted Successfully");
            }
            catch (FileUploadException | SQLException e) {
                response.sendRedirect(getServletContext().getContextPath()+"/problems?number="+number+"&message=Failed to Submit");


            }


        }

    }

}
