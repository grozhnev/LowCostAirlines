package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(exclude={"ticketId"})
@Accessors(chain = true)
public class Ticket {
    private Long ticketId;
    private Long flightId;
    private Long customerId;
    private Long price;
    private Long luggagePrice;
    private Boolean registrationPriority;
}
