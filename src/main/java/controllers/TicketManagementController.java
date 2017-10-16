package controllers;

import dao.FlightDAO;
import dao.TicketDAO;
import entities.Airport;
import entities.Flight;
import entities.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

@WebServlet("/ticketmanagement")
public class TicketManagementController extends HttpServlet {

    private Set<Flight> flights;
    private FlightDAO flightDAO = new FlightDAO();
    private TicketDAO ticketDAO = new TicketDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            flights = flightDAO.getAll();
            request.setAttribute("flights", flights);
            request.getRequestDispatcher("/ticketmanagement.jsp").forward(request, response);
        } catch (SQLException e) {
            System.err.println(e);
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
                        .setRegistrationPriority(requestedRegistrationPriority != null)
                        .setPrice(100)
                        .setLuggagePrice(Integer.valueOf(requestedWeight))
                        .setCustomerId(1)
                        .setFlightId(requestedFlight.getFlightId());
                try {
                    ticketDAO.insert(requestedTicket);
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        }
    }
}
