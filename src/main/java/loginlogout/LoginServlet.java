package loginlogout;

import dao.CustomerDAO;
import entities.Customer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


public class LoginServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //req.getRequestDispatcher("login.jsp").forward(req, resp);
        LOGGER.info("Login page");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("login.jsp").include(request, response);

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LOGGER.info("Got parameters: email=" + email + " password=" + password);

        try {
            Customer customer = new CustomerDAO()
                    .getAll()
                    .stream()
                    .filter(o -> o.getEmail().equals(email) && o.getPassword().equals(password))
                    .findFirst()
                    .get();
            if (customer != null) {
                LOGGER.info("Got customer=" + customer);
                out.print("Welcome, " + email);
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setMaxInactiveInterval(15);
                Cookie userName = new Cookie("email", email);
                response.addCookie(userName);
                LOGGER.info("Added cookie="+userName);

                LOGGER.info("Redirecting to /ticketmanagement");
                response.sendRedirect("/ticketmanagement");
            } else {
                response.sendRedirect("/");
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
        }

        out.close();
    }
}
