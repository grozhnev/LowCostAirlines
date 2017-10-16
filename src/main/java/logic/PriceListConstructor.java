package logic;

import dao.FlightDAO;
import dao.PlaneDAO;
import dao.TicketDAO;
import entities.Flight;
import entities.Plane;
import entities.Ticket;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.util.*;

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

    /* NO MORE IN USE ?
     *
     *The method for parsing tickets list in single entities for the further work with price in each*/
    public void getTickets() {}

    /*
	- какая базовая цена? long (Double ?)
	- прописать параметры её изменения
				а1)время DateTime

					время1
					время покупки - систменое время запроса
									DateThu, 12 Oct 2017 11:19:16 GMT

					время2 мета-время из запроса

					время Итог: время2-время1

					дни до вылета (функция подсчета времени)
					а2) функция коэффициента (price - (days * (price/20)) )
						String.parseDate?()

				б) вес багажа (weight w <=10 *1; (10< w) = (w-10)*(price/100))   long
				в) приоритет (bool) * 1,6
*/

    /*
    * NEEDS to tune connection with real db-data.
    * Where is starter ?
    * (then, divide into couple methods).
    */
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
                    ticket.setPrice(ticket.getPrice() + ticket.getLuggagePrice() + PriceListConstructor.basicLuggageFee);
                } else {
                    ticket.setPrice(ticket.getPrice() + PriceListConstructor.basicPrice);
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
