package filters;

import database.GetConnection;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

public class AdminLoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute("admin")!=null){
            filterChain.doFilter(request,response);
            return;
        }
        Properties properties = GetConnection.getProperties();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null) {

            response.sendRedirect(request.getServletContext().getContextPath()+"/admin_login.jsp");
            return;
        }

        String actual_username = properties.getProperty("username");
        String actual_password = properties.getProperty("password");
        if (username.equals(actual_username) && password.equals(actual_password)){
            session.setMaxInactiveInterval(7200);
            session.setAttribute("admin",true);
            filterChain.doFilter(request,response);
            return;
        }
        response.sendRedirect(request.getServletContext().getContextPath()+"/admin_login.jsp?message=Login Failed");

    }

    @Override
    public void destroy() {

    }
}
