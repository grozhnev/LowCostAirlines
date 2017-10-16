package mockito;

import dao.TicketDAO;
import entities.Ticket;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import services.ConnectionFactory;

import java.sql.DriverManager;
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

    @Test
    public void testConnectionFactory()throws SQLException{

        final String URL = "jdbc:mysql://localhost:3306/lowcostairlines"; /*lowcostairlines*/ /*ticketsystem*/
        final String USER = "testuser";
        final String PASS = "testpassword";

        when(ConnectionFactory.getConnection()).thenReturn(DriverManager.getConnection(URL, USER, PASS));
        Assert.assertEquals(DriverManager.getConnection(URL, USER, PASS), ConnectionFactory.getConnection());
    }
}
