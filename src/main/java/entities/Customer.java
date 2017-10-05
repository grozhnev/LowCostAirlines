package entities;

public class Customer {
    private int customerId;
    private boolean isAdmin;
    private String customerName;
    private String LastName;
    private String personId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", isAdmin=" + isAdmin +
                ", customerName='" + customerName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", personId='" + personId + '\'' +
                '}';
    }
}
