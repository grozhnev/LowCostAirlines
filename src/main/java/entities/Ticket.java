package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(exclude={"ticketId"})
@Accessors(chain = true)
public class Ticket {
    private int ticketId;
    private int flightId;
    private int customerId;
    private int price;
    private int luggagePrice;
    private boolean registrationPriority;
}
