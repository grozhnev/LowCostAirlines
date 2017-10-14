package loginlogout;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Allows Customer enter to system and see information.
 * Now to enter you can input any name and password 123.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("login.jsp").include(request, response);

        String name = request.getParameter("uname");
        String password = request.getParameter("upass");

        if (password.equals("123")) {
            out.print("Welcome, " + name);
            HttpSession session = request.getSession();
            session.setAttribute("uname", name);
            response.sendRedirect("/data");
        } else {
            request.getRequestDispatcher("unsuccessfully.html").forward(request, response);
        }
        out.close();
    }
}