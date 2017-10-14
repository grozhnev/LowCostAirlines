package loginlogout;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

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

    /**
     * Get from registration page Customers parameters an put them in map
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("registration.jsp").include(request, response);

        String name = request.getParameter("uname");
        String password = request.getParameter("upass");

        if ("123".equals(password)) {
            out.print("Welcome, " + name);
            HttpSession session = request.getSession();
            session.setAttribute("uname", name);
            response.sendRedirect("/login");
        }
        out.close();
    }
}
