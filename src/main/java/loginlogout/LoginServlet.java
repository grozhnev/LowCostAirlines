package loginlogout;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final String userID = "admin";
    private final String password = "123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("login.jsp").include(request, response);

        String name = request.getParameter("uname");
        String password = request.getParameter("upass");

        if (name.equals(userID) && password.equals(this.password)) {
            out.print("Welcome, " + name);
            HttpSession session = request.getSession();
            session.setAttribute("uname", name);
            session.setMaxInactiveInterval(15);
            Cookie userName = new Cookie("uname", name);
            response.addCookie(userName);

            response.sendRedirect("/data");
        } else {
            response.sendRedirect("/login");
        }
        out.close();
    }
}
