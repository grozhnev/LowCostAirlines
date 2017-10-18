package loginlogout;

import dao.CustomerDAO;
import dao.DAO;
import entities.Customer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.*;


public class LoginServlet extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private DAO<Customer> customerDAO = new CustomerDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp").include(request, response);
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Set<Customer> customerSet = customerDAO.getAll();
            for (Customer customer : customerSet) {
                if (email.equals(customer.getEmail()) && password.equals(customer.getPassword())) {
                    Cookie userName = new Cookie("email", email);
                    response.addCookie(userName);
                    LOGGER.info("Login sucess for email=" + email);
                    response.sendRedirect("/ticketmanagement");
                }
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
        }
    }
}