package mockito;

import dao.TicketDAO;
import entities.Ticket;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CheckPriceTest {
    @Mock
    Ticket ticket;

    @Test
    public void checkPrice()throws SQLException {
        when(ticket.getPrice()).thenReturn(50L);
        Assert.assertEquals(Long.valueOf(50L), ticket.getPrice());
    }
}
