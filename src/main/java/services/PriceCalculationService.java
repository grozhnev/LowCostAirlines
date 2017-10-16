package services;

import dao.FlightDAO;
import dao.PlaneDAO;
import dao.TicketDAO;
import entities.Flight;
import entities.Plane;
import entities.Ticket;
import lombok.Data;

import java.sql.SQLException;
import java.util.*;

/**
 * Description:
 *  This class "PriceCalculationService" construct the list of Tickets that matches customer request.
 *  List of Tickets that match Customer request with individual price will be sent to Browser as final respond of
 *  LowCostService.
 */

@Data
public class PriceCalculationService {

    private static int basicPrice = 100;
    private static int basicLuggageFee = 50;

    private int price; /* required to be Long */
    private int daysBeforeFlight;

    private int luggageWeight;
    private double planeLoadRate;

    private boolean isHighPriority;
    private boolean hasLuggage;

    private Date time;

    private Set<Ticket> ticketContainer;
    private Map<Ticket, Flight> flightsMap;
    private Map<Ticket, Plane> planesMap;

    TicketDAO ticketDAO = new TicketDAO();
    FlightDAO flightDAO = new FlightDAO();
    PlaneDAO planeDAO = new PlaneDAO();


    public void calculatePrice() throws SQLException {
        ticketContainer = new HashSet<>();
        try {
            if (ticketDAO.getAll().size() != 0) {
                ticketContainer.addAll(new TicketDAO().getAll());
            }

            for (Ticket ticket: ticketContainer) {

                if(ticket.isRegistrationPriority()){
                    ticket.setPrice(ticket.getPrice() * 2);
                }

                if (ticket.getLuggagePrice() > 0){
                    ticket.setPrice(ticket.getPrice() + ticket.getLuggagePrice() + PriceCalculationService.basicLuggageFee);
                } else {
                    ticket.setPrice(ticket.getPrice() + PriceCalculationService.basicPrice);
                }

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void fillFLightsAndPlanesMap() throws SQLException{
        flightsMap = new HashMap<>();
        planesMap = new HashMap<>();
        
    }
}