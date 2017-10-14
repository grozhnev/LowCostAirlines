import dao.AirportDAO;
import dao.CustomerDAO;
import dao.DAO;
import dao.PlaneDAO;

import entities.Airport;
import entities.Customer;
import entities.Plane;
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
 * Temporary class
 * Shows all results from schema
 */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection connection = ConnectionFactory.getConnection();
        DAO<Airport> airport = new AirportDAO();
        DAO<Plane> plane = new PlaneDAO();
        DAO<Customer> customer = new CustomerDAO();

        try {
            req.setAttribute("airports", airport.getAll());
            req.setAttribute("planes", plane.getAll());
            req.setAttribute("customers", customer.getAll());
            req.getRequestDispatcher("sucsesslogin.jsp").forward(req, resp);

        } catch (SQLException e) {
            System.out.println("not connect");
            req.setAttribute("error", e.getErrorCode());
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
        }
    }
}
