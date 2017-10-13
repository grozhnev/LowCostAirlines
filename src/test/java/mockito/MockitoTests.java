package mockito;

import dao.TicketDAO;
import entities.Ticket;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
//import static org.junit.Assert.*;

/**
 * Class:
 * Version: 0.1
 * <p>
 * Created by: Georgii Rozhnev, https://github.com/grozhnev
 * Date:
 * <p>
 * Description:
 */

public class MockitoTests {

    TicketDAO ticketDAO = new TicketDAO();
    @Mock
    Ticket ticket = mock(Ticket.class);

  /*  @Test
    public void priceCheckMockitoTest(){

        Ticket ticket = Mockito.mock(Ticket.class);
        when(ticketDAO.getById())

    }*/
}
