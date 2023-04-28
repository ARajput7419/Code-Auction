package servlets;

import database.GetConnection;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Properties;

public class DownloadTemplate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("team") == null){

            response.sendRedirect(request.getServletContext().getContextPath()+"/login");
            return;

        }

        String param_id = request.getParameter("number");
        int number = 1;
        try{
            number = Integer.parseInt(param_id);
            Properties properties = GetConnection.getProperties();
            String template_dir = properties.getProperty("templates");
            String baseDir = request.getServletContext().getRealPath("/");
            File file =  new File(baseDir+"/"+template_dir+"/"+number+".txt");
            if (file.exists()){

                String mime_type = getServletContext().getMimeType(baseDir+"/"+template_dir);
                response.setContentType(mime_type);
                response.setContentLength((int) file.length());
                response.setHeader("Content-Disposition","attachment; filename=\""+file.getName()+"\"");
                ServletOutputStream outputStream = response.getOutputStream();
                FileInputStream inputStream = new FileInputStream(file);
                outputStream.write(inputStream.readAllBytes());
                outputStream.close();
                response.sendRedirect(getServletContext().getContextPath()+"/problems?number="+number+"&message=Downloaded Successfully");
            }
            else{
                response.sendRedirect(getServletContext().getContextPath()+"/problems?number="+number+"&message=Does not exists");
            }

        }
        catch (Exception e){
            response.sendRedirect(getServletContext().getContextPath()+"/problems?number="+number+"&message=Failed !!!");
        }
    }
}
