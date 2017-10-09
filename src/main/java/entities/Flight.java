package entities;

import java.time.*;

public class Flight {
    /*
    CREATE TABLE Flight
    (
      Id                   INT AUTO_INCREMENT,
      TimeOfDeparture      DATETIME(10),
      AirportOfDeparture   INT,
      AirportOfArrival     INT,
      PlaneId              INT,
      PRIMARY KEY (Id)
    );*/

    /*UNIQUE*/
    private LocalDateTime departureTime;

    /*RELATIONAL*/
    private int id;
    private String airportOfDeparture;
    private String airportOfArrival;
    private int planeId;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getAirportOfDeparture() {
        return airportOfDeparture;
    }
    public void setAirportOfDeparture(String airportOfDeparture) {
        this.airportOfDeparture = airportOfDeparture;
    }

    public String getAirportOfArrival() {
        return airportOfArrival;
    }
    public void setAirportOfArrival(String airportOfArrival) {
        this.airportOfArrival = airportOfArrival;
    }

    public int getPlaneId() {
        return planeId;
    }
    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }
}
