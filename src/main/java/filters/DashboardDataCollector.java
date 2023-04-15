package filters;
import database.dao.TeamDAO;
import database.entity.Team;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DashboardDataCollector implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        TeamDAO dao = new TeamDAO();
        try {
            List<Team> results = dao.getAllTeams();
            request.setAttribute("results",results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
