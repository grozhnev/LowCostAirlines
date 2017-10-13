package logic;

import dao.TicketDAO;
import entities.Ticket;
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


public class PriceListConstructor{
    /**
     * TEST FIRST (Mock test to ensure that we have access to DB).
     * */

    @Getter @Setter private int price; /* required to be Long */
    private Date time;
    private boolean isHighPriority;

    private Set<Ticket> ticketContainer;

    public void getTickets() throws SQLException {

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
