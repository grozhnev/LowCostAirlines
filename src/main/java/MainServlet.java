import dao.*;
import entities.Airport;
import entities.Customer;
import entities.Plane;
import entities.Ticket;
import services.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection connection = ConnectionFactory.getConnection();
        DAO<Airport> airport = new AirportDAO();
        DAO<Plane> plane = new PlaneDAO();
        DAO<Customer> customer = new CustomerDAO();
        DAO<Ticket> ticket = new TicketDAO();

        try {
            req.setAttribute("metadata", connection.getMetaData());
            req.setAttribute("airports", airport.getAll());
            req.setAttribute("planes", plane.getAll());
            req.setAttribute("customers", customer.getAll());
            req.setAttribute("tickets", ticket.getAll());
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);

        } catch (SQLException e) {
            req.setAttribute("error", e.getErrorCode());
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
        }
    }
}
