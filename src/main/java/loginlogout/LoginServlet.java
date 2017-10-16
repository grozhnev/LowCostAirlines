package loginlogout;

import dao.CustomerDAO;
import entities.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("login.jsp").include(request, response);

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Customer customer = new CustomerDAO()
                    .getAll()
                    .stream()
                    .filter(o -> o.getEmail().equals(email) && o.getPasswd().equals(password))
                    .findFirst()
                    .get();
            if (customer != null) {
                out.print("Welcome, " + email);
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setMaxInactiveInterval(15);
                Cookie userName = new Cookie("email", email);
                response.addCookie(userName);

                response.sendRedirect("/ticketmanagement");
            } else {
                response.sendRedirect("/");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }


        out.close();
    }
}
