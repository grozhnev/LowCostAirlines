package loginlogout;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("mainpage.jsp").include(request, response);

        String name = request.getParameter("uname");
        String password = request.getParameter("upass");

        if (password.equals("123")) {
            out.print("Welcome, " + name);
            HttpSession session = request.getSession();
            session.setAttribute("uname", name);
            response.sendRedirect("/Data");
        } else {
            request.getRequestDispatcher("unsucsessfulllogin.html").forward(request, response);
        }
        out.close();
    }

}
