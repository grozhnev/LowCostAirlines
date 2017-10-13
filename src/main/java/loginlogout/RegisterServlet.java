package loginlogout;

import mapWithNameAndPassword.CustomerNameAndPass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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

    private CustomerNameAndPass customerNameAndPass = new CustomerNameAndPass();

    /**
     * Get from registration page Customers parameters an put them in map
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("registration.jsp").include(request, response);

        String name = request.getParameter("uname");
        String password = request.getParameter("upass");

        customerNameAndPass.customersNamesAndPasswords(name, password);
        customerNameAndPass.mapToString();

        //This cycle works in any case because name from request is the same
        //entry.getKey(). Now I have not idea how solve this issue.
        for (Map.Entry<String, String> entry : CustomerNameAndPass.map.entrySet()) {
            if ((entry.getKey()).equals(name)) {
                out.print("Welcome, " + name);
                HttpSession session = request.getSession();
                session.setAttribute("uname", name);
                response.sendRedirect("/login");
            }
        }
        out.close();
    }
}
