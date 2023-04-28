package filters;

import database.dao.IpDAO;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class IPFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    IpDAO dao = new IpDAO();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        try {
            ArrayList<String> ips = dao.getIps();
            for (String ip : ips){
                if (ipAddress.equals(ip)){
                    filterChain.doFilter(request,response);
                    return;
                }
            }
            response.getWriter().write("<h1>Not Allowed to Access</h1>");
        }
        catch (Exception e){
            response.getWriter().write("<h1>Try Again...</h1>");
        }
    }

    @Override
    public void destroy() {

    }
}
