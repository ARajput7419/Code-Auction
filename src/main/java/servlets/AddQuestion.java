package servlets;

import database.dao.AssignedDAO;
import database.dao.QuestionDAO;
import database.entity.Assigned;
import database.entity.Question;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddQuestion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
            String statement = null;
            String level = null;
            int number = 0;
            try {
                List<FileItem> items = fileUpload.parseRequest(request);
                for (FileItem item : items) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    switch (name) {

                        case "question": {
                            statement = new String(item.getInputStream().readAllBytes());
                            break;
                        }
                        case "level" :{
                            level = value;
                            break;
                        }

                        case "number" :{
                            number = Integer.parseInt(value);
                            break;
                        }


                    }


                }

                QuestionDAO dao = new QuestionDAO();
                Question question = new Question();
                question.setStatement(statement);
                question.setId(number);
                question.setLevel(level);
                dao.insert(question);
                response.sendRedirect(getServletContext().getContextPath()+"/admin.jsp?message=Added Successfully");

            }
            catch (FileUploadException | SQLException e) {

                response.sendRedirect(getServletContext().getContextPath()+"/admin.jsp?message=Failed to Add Question");

            }


        }



    }
}
