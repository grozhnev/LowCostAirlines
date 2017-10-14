package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(exclude={"customerId",
        "firstName",
        "lastName",
        "passport",
        "passwd"})
@Accessors(chain = true)
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String passport;
    private String email;
    private String passwd;
}
