package logic;

import dao.TicketDAO;
import entities.Ticket;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by: Georgii Rozhnev, https://github.com/grozhnev
 * Date: 2017-10-12
 * <p>
 * Description:
 *  Construct the list of Tickets that matches customer request.
 */
public class PriceListConstructor{
    /**
     * TEST FIRST (Mock test to ensure that we have access to DB).
     * */

    private Long price;
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
