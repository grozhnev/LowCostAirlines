package entities;

public class Customer {

    private boolean isAdmin;

    private int customerId;

    private String customerFirstName;
    private String customerLastName;
    private String customerPersonId;

    public Customer() {
    }

    public Customer(int customerId, String customerFirstName,
                    String customerLastName, String customerPersonId) {
        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerPersonId = customerPersonId;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }
    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }
    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerPersonId() {
        return customerPersonId;
    }
    public void setCustomerPersonId(String customerPersonId) {
        this.customerPersonId = customerPersonId;
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
                "customerId=" + customerId +
                ", customerFirstName='" + customerFirstName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", customerPersonId='" + customerPersonId + '\'' +
                '}';
    }
}
