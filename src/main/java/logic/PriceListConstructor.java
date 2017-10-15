package logic;

import dao.TicketDAO;
import entities.Ticket;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 *  This class "PriceListConstructor" construct the list of Tickets that matches customer request.
 *  List of Tickets that match Customer request with individual price will be sent to Browser as final respond of
 *  LowCostService.
 */


public @Data class PriceListConstructor{
    /**
     * TEST FIRST (Mock test to ensure that we have access to DB).
     *
     *  0) if respond == true
     *  1) read request data (luggage) and respond
     *  2) count the price from respond, calculate changes based on properties ->
     *  3) function of price calculation
     *  4) return price / or FLight object
     * */


    /*ISOLATED LOGIC:
    * 1) from list of tickets, received from the HttpRespond
    * 2) from each ticket in received ticket list we take the flight.
    * 3) each flight has the parameters that are being used in priceCalculation for the flight in ticket
    * 4) priceCalculation() method gets _____, _____, ____ as input from flight and changes the price
    * 5) as a result PriceCalculation class transfers the updated list of tickets to the output of the user.
    * */
    private int price; /* required to be Long */
    private Date time;
    private boolean isHighPriority;

    private Set<Ticket> ticketContainer;


    public void getTicketsZZz() throws SQLException {

        ticketContainer = new HashSet<>();
        TicketDAO ticketDAO = new TicketDAO();
        try {
            if (ticketDAO.getAll().size() != 0) {
                ticketContainer.addAll(new TicketDAO().getAll());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void calculatePrice(){

    }
}
