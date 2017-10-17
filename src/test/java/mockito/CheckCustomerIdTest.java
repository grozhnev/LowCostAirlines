package mockito;

import entities.Ticket;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.mockito.Mockito.when;


public class CheckCustomerIdTest {

    @Mock
    Ticket ticket = Mockito.mock(Ticket.class);

    @Test
    public void checkCustomerId()throws SQLException {
        when(ticket.getCustomerId()).thenReturn(1L);
        Assert.assertEquals(Long.valueOf(1L), ticket.getCustomerId());
    }
}
