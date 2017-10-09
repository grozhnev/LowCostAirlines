package entities;

public class Customer {
    /*
    CREATE TABLE Customer
    (
      Id             INT AUTO_INCREMENT,
      IsAdmin        BOOL,
      FirstName      VARCHAR(45),
      LastName       VARCHAR(45),
      PassportId     VARCHAR(45),
      PRIMARY KEY (Id)
    );*/

    private int id;
    private boolean isAdmin;
    private String firstName;
    private String lastName;
    private String personId;

    public Customer() {
    }

    public Customer(int id, String firstName,
                    String lastName, String personId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonId() {
        return personId;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personId='" + personId + '\'' +
                '}';
    }
}
