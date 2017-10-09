package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Airport entity implementation
 * @author klysov
 */

@Data
@EqualsAndHashCode(exclude={"airportId"})
public class Airport {
    private int airportId;
    private String name;
}
