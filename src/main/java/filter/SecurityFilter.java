package filter;

import dao.CustomerDAO;
import dao.DAO;
import entities.Customer;
import exception.NotLoggedException;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public class SecurityFilter implements Filter {

    private DAO<Customer> customerDAO = new CustomerDAO();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Cookie[] cookies = req.getCookies();

        boolean logged = false;

        try {
            Set<Customer> customerSet = customerDAO.getAll();
            for (Customer customer : customerSet) {
                if (cookies[1].getValue().equals(customer.getEmail())) {
                    //chain.doFilter(req, res);
                    logged = true;
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (logged) {
            chain.doFilter(request, response);
        } else {
            throw new NotLoggedException("Forbidden");
        }

    }

    public void destroy() {}

    public void init(FilterConfig filterConfig) throws ServletException {}


}
