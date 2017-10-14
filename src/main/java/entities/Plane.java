package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(exclude={"currentLoad"})
@Accessors(chain = true)
public class Plane {
    private int planeId;
    private int maxLoad;
    private int currentLoad;
}
