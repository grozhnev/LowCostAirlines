import dao.AirportDaoImpl;
import dao.CustomerDAOImpl;
import dao.DAO;
import dao.PlaneDaoImpl;
import entities.Customer;
import services.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/Data")
public class DataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection connection = ConnectionFactory.getConnection();
        AirportDaoImpl airport = new AirportDaoImpl();
        PlaneDaoImpl plane = new PlaneDaoImpl();
        DAO<Customer> customer = new CustomerDAOImpl();

        try {
            req.setAttribute("metadata", connection.getMetaData());
            req.setAttribute("airports", airport.getAllAirports());
            req.setAttribute("planes", plane.getAllPlanes());
            req.setAttribute("customers", customer.getAll());
            req.getRequestDispatcher("datafromshema.jsp").forward(req, resp);

        } catch (SQLException e) {
            req.setAttribute("error", e.getErrorCode());
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
        }
    }
}
