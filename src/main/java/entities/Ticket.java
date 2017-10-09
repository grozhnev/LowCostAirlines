package entities;

public class Ticket {

    private int ticketId;
    private int flightId;
    private int customerId;
    private long Price;
    private int LuggagePrice;
    private boolean RegistrationPriority;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int idTicket) {
        this.ticketId = idTicket;
    }

    public void setFlightId(int idFlight) {
        this.flightId = idFlight;
    }

    public void setCustomerId(int idCustomer) {
        this.customerId = idCustomer;
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

    public int getFlightId() {

        return flightId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public long getPrice() {
        return Price;
    }

    public int getLuggagePrice() {
        return LuggagePrice;
    }

    public boolean isRegistrationPriority() {
        return RegistrationPriority;
    }
}
