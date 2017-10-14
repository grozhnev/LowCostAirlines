package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude={"ticketId"})
public class Ticket {
    private int ticketId;
    private int flightId;
    private int customerId;
    private int price;
    private int luggagePrice;
    private boolean registrationPriority;
}
