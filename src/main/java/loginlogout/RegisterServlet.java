package loginlogout;

import dao.CustomerDAO;
import dao.DAO;
import entities.Customer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Allows new Customer to register
 * <p>
 * If Customer already exists, he transfers to login page
 * <p>
 * If the Customer does not exist, it is first added to the map
 * and then transferred to the login page
 */
@WebServlet("/registration")
public class RegisterServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    /**
     * Get from registration page Customers parameters an put them in map
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("registration.jsp").include(request, response);

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String passport = request.getParameter("passport");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            DAO<Customer> customerDAO = new CustomerDAO();
            Customer newCustomer = new Customer()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(email)
                    .setPassport(passport)
                    .setPassword(password);
            customerDAO.insert(newCustomer);
            out.print("Welcome, " + email);
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            response.sendRedirect("/");
        } catch (SQLException e) {
            System.err.println(e);
        }
        out.close();
    }
}
