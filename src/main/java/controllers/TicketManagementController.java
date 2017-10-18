package controllers;

import dao.CustomerDAO;
import dao.FlightDAO;
import dao.TicketDAO;
import entities.Airport;
import entities.Customer;
import entities.Flight;
import entities.Ticket;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/ticketmanagement")
public class TicketManagementController extends HttpServlet {

    private Set<Flight> flights;
    private Set<Ticket> tickets = new HashSet<>();
    private FlightDAO flightDAO = new FlightDAO();
    private TicketDAO ticketDAO = new TicketDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private int customerId = 0;
    static final Logger LOGGER = Logger.getLogger(TicketManagementController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = Arrays.stream(request.getCookies())
                .filter(o -> o.getName().equals("email")).findFirst()
                .get()
                .getValue();
        LOGGER.info("Cookie email=" + email);

        try {
            flights = flightDAO.getAll();

            Iterator<Ticket> ticketIterator = ticketDAO.getAll()
                    .stream()
                    .filter(o -> o.getCustomerId() == customerId)
                    .iterator();

            while (ticketIterator.hasNext()) {
                tickets.add(ticketIterator.next());
            }
            customerId = customerDAO.getAll()
                    .stream()
                    .filter(o -> o.getEmail().equals(email))
                    .findFirst()
                    .get()
                    .getCustomerId();
        } catch (SQLException e) {
            LOGGER.warn(e);
        }

        request.setAttribute("flights", flights);
        request.setAttribute("tickets", tickets);
        request.getRequestDispatcher("/ticketmanagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);

        String requestedFlightString = request.getParameter("checkradio");
        String requestedRegistrationPriority = request.getParameter("registrationpriority");
        String requestedWeight = request.getParameter("weight");
        if (!requestedFlightString.isEmpty()) {
            Flight requestedFlight = null;

            for (Flight flight : flights) {
                if (flight.toString().equals(requestedFlightString)) {
                    requestedFlight = flight;
                    break;
                }
            }

            if (requestedFlight != null) {
                Ticket requestedTicket = new Ticket()
                        .setRegistrationPriority(requestedRegistrationPriority != null)
                        .setPrice(100)
                        .setLuggagePrice(Integer.valueOf(requestedWeight))
                        .setCustomerId(customerId)
                        .setFlightId(requestedFlight.getFlightId());
                try {
                    ticketDAO.insert(requestedTicket);
                } catch (SQLException e) {
                    LOGGER.warn(e);
                }
            }
        }
    }
}
