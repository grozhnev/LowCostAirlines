package entities;

public class Customer {

    private int customerId;
    private boolean isAdmin;
    private String customerName;
    private String customerLastName;
    private String customerPersonId;

    public Customer() {
    }

    public Customer(int customerId, boolean isAdmin, String customerName,
                    String customerLastName, String customerPersonId) {
        this.customerId = customerId;
        this.isAdmin = isAdmin;
        this.customerName = customerName;
        this.customerLastName = customerLastName;
        this.customerPersonId = customerPersonId;
    }

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

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", isAdmin=" + isAdmin +
                ", customerName='" + customerName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", customerPersonId='" + customerPersonId + '\'' +
                '}';
    }
}
