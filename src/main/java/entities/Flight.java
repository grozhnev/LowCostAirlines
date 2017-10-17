package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(exclude={"flightId"})
@Accessors(chain = true)
public class Flight {
    private Long flightId;
    private LocalDateTime dateTime;
    private Long airportSource;
    private Long airportDestination;
    private Long planeId;
    private Long price;
}
