package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(exclude={"airportId"})
@Accessors(chain = true)
public class Airport {
    private Long airportId;
    private String name;
}
