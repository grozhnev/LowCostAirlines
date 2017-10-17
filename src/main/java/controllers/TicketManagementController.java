package controllers;

import dao.FlightDAO;
import dao.PlaneDAO;
import dao.TicketDAO;
import entities.Flight;
import entities.Plane;
import entities.Ticket;
import org.apache.log4j.Logger;
import services.PriceCalculation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/ticketmanagement")
public class TicketManagementController extends HttpServlet {

    private Set<Flight> flights;
    private final FlightDAO flightDAO = new FlightDAO();
    private final TicketDAO ticketDAO = new TicketDAO();
    private final PlaneDAO planeDAO = new PlaneDAO();

    private final Set<Ticket> setOfTickets = new HashSet<>();
    private final Set<Plane> setOfPlanes = new HashSet<>();

    private final Map<Flight, Ticket> ticketByFlight = new HashMap();
    private final Map<Ticket, Long> luggageByTicket = new HashMap();
    private final Map<Ticket, Boolean> priorityByTicket = new HashMap();
    private final Map<Flight, Plane> planeByFlight = new HashMap<>();
    private final Map<Plane, Double> loadByPlane = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(TicketManagementController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            flights = flightDAO.getAll();

            for (Flight flight : flights) {
                setOfTickets.add(ticketDAO.getById(flight.getFlightId()));
                setOfPlanes.add(planeDAO.getById(flight.getPlaneId()));
                planeByFlight.put(flight, planeDAO.getById(flight.getPlaneId()));
                ticketByFlight.put(flight, ticketDAO.getById(flight.getFlightId()));
            }

            for (Ticket ticket: setOfTickets) {
                luggageByTicket.put(ticket, ticket.getLuggagePrice());
                priorityByTicket.put(ticket, ticket.getRegistrationPriority());
            }

            for (Plane plane : setOfPlanes) {
                loadByPlane.put(plane, plane.getCurrentLoad());
            }

            PriceCalculation priceCalculation = new PriceCalculation();
            priceCalculation.setPriceInFlights(flights, ticketByFlight, luggageByTicket, priorityByTicket,
                                                planeByFlight,loadByPlane);

            request.setAttribute("flights", flights);
            request.getRequestDispatcher("/ticketmanagement.jsp").forward(request, response);
        } catch (SQLException e) {
            LOGGER.warn(e);
        }
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
                        .setRegistrationPriority(true)
                        .setPrice(359L)
                        .setLuggagePrice(228L)
                        .setCustomerId(1L)
                        .setRegistrationPriority(requestedRegistrationPriority != null)
                        .setPrice(100L)
                        .setLuggagePrice(Long.valueOf(requestedWeight))
                        .setCustomerId(1L)
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
