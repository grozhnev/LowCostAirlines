package filter;

import dao.CustomerDAO;
import dao.DAO;
import entities.Customer;
import exception.NotLoggedException;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public class SecurityFilter implements Filter {
    static final Logger LOGGER = Logger.getLogger(SecurityFilter.class);
    private DAO<Customer> customerDAO = new CustomerDAO();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Cookie[] cookies = req.getCookies();

        boolean logged = false;

        try {
            Set<Customer> customerSet = customerDAO.getAll();
            for (Customer customer : customerSet) {
                for (Cookie cookie: cookies) {
                    if (cookie.getName().equals("email"))
                        if (customer.getEmail().equals(cookie.getValue())) {
                            LOGGER.info("Logged in");
                            logged = true;
                        }
                }
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
        }

        if (logged) {
            chain.doFilter(request, response);
        } else {
            LOGGER.warn("Not logged, forbidden");
            throw new NotLoggedException("Forbidden");
        }

    }

    public void destroy() {}

    public void init(FilterConfig filterConfig) throws ServletException {}


}
