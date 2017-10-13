package mockito;

import dao.TicketDAO;
import entities.Ticket;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.mockito.Mockito.*;
//import static org.junit.Assert.*;

public class MockitoTests {
    TicketDAO ticketDAO = new TicketDAO();
    @Mock
    Ticket ticket = mock(Ticket.class);

    @Test
    public void checkCustomerId()throws SQLException{
        Ticket ticket = Mockito.mock(Ticket.class);
        when(ticket.getCustomerId()).thenReturn(1);
        Assert.assertEquals(1, ticket.getCustomerId());
    }

    @Test
    public void checkPrice()throws SQLException{
        Ticket ticket = mock(Ticket.class);
        when(ticket.getPrice()).thenReturn(50);
        Assert.assertEquals(50, ticket.getPrice());
    }
}
