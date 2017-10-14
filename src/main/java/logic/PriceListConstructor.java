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
     *
     *  1) перечислить мета-данные на вход
     *  2) написать логику их обработки
     *  3) функция подсчета цены
     *  4) написать логику их возврата
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
