package servlets;

import database.GetConnection;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

            List<File> fileList = new ArrayList<>();
            fileList.add(new File(baseDir+"/"+template_dir+"/JAVA/"+number+".txt"));
            fileList.add(new File(baseDir+"/"+template_dir+"/C++/"+number+".txt"));
            fileList.add(new File(baseDir+"/"+template_dir+"/PYTHON/"+number+".txt"));
            File zipFile = File.createTempFile("template_"+number, ".zip");
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            int i = 0;

            String ext [] = {"java","cpp","py"};

            for (File file : fileList) {
                String name = file.getName().replace("txt",ext[i++]);
                ZipEntry zipEntry = new ZipEntry(name);
                zos.putNextEntry(zipEntry);
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                fis.close();
                zos.closeEntry();
            }
            zos.close();
            fos.close();
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\"template_"+number+".zip\"");

            FileInputStream fis = new FileInputStream(zipFile);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                response.getOutputStream().write(buffer, 0, len);
            }
            fis.close();


        }
        catch (Exception e){
            response.sendRedirect(getServletContext().getContextPath()+"/problems?number="+number+"&message=Failed...");
        }
    }
}
