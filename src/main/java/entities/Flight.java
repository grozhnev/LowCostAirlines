package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(exclude={"flightId"})
public class Flight {
    private int flightId;
    private LocalDateTime dateTime;
    private int airportSource;
    private int airportDestination;
    private int planeId;
}
