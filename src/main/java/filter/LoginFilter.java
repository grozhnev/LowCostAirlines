package filter;

import dao.CustomerDAO;
import entities.Customer;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class LoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        doFilter(request, response, chain);
    }

    public void destroy() {

    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
