package entities;

public class Ticket {

    private int idTicket;
    private int idFlight;
    private int idCustomer;
    private int Price;
    private int LuggagePrice;
    private boolean RegistrationPriority;

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setLuggagePrice(int luggagePrice) {
        LuggagePrice = luggagePrice;
    }

    public void setRegistrationPriority(boolean registrationPriority) {
        RegistrationPriority = registrationPriority;
    }

    public int getIdFlight() {

        return idFlight;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public int getPrice() {
        return Price;
    }

    public int getLuggagePrice() {
        return LuggagePrice;
    }

    public boolean isRegistrationPriority() {
        return RegistrationPriority;
    }
}


