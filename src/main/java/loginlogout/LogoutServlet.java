package loginlogout;

import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */

public class LogoutServlet extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(LogoutServlet.class);

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("logout.jsp").include(req, resp);

        Cookie cookie = new Cookie("email", "");
        LOGGER.info("Logout");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        resp.sendRedirect("/");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}