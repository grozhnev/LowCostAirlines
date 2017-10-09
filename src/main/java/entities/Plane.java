package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Plane entity implementation
 * @author klysov
 */

@Data
@EqualsAndHashCode(exclude={"currentLoad"})
public class Plane {
    private int planeId;
    private int maxLoad;
    private int currentLoad;
}
