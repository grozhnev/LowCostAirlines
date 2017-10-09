package entities;

public class Ticket {
    /*
    CREATE TABLE Ticket
    (
      Id                 INT AUTO_INCREMENT,
      FlightId                 INT,
      CustomerId               INT,
      PriceForTicket           INT,
      PriceForLuggage          INT,
      PriorityForRegistration  BOOL,
      PRIMARY KEY (Id)
    );*/

    private int id;
    private int flightId;
    private int customerId;
    private int price;
    private int priceForLuggage;

    private boolean priorityForRegistration;


}
