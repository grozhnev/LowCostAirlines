import services.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Simple servlet class for testing
 * @author
 */
@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection connection = ConnectionFactory.getConnection();
        try {
            req.setAttribute("metadata", connection.getMetaData());
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
        } catch (SQLException e) {
            req.setAttribute("error", e.getErrorCode());
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
        }

    }
}
