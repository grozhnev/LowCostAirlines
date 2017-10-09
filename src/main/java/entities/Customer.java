package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude={"customerId"})
public class Customer {
    private int customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerPersonId;
}
