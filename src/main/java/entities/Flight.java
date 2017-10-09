package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(exclude={"flightId"})
public class Flight {
    private int flightId;
    private Date dateTime;
    private int airportSource;
    private int airportDestination;
    private int planeId;
}
