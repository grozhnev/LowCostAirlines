package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(exclude={"flightId"})
@Accessors(chain = true)
public class Flight {
    private int flightId;
    private LocalDateTime dateTime;
    private int airportSource;
    private int airportDestination;
    private int planeId;
}
