package entities;

import java.time.*;

public class Flight {

    /* model:

     CREATE TABLE Flight
    (
       idFlight             INT NOT NULL AUTO_INCREMENT,
       DateTime             DATETIME(6) NOT NULL,
       Airport_Source       INT NOT NULL,
       Airport_Destination  INT NOT NULL,
       idPlane              INT NOT NULL,
       PRIMARY KEY (idFlight)
    );*/



    private int idFlight;
    private LocalDateTime dateTime;
    private String airportDeparture;
    private String airportArrival;
    private int planeId;


    public int getIdFlight() {
        return idFlight;
    }
    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getAirportDeparture() {
        return airportDeparture;
    }
    public void setAirportDeparture(String airportDeparture) {
        this.airportDeparture = airportDeparture;
    }

    public String getAirportArrival() {
        return airportArrival;
    }
    public void setAirportArrival(String airportArrival) {
        this.airportArrival = airportArrival;
    }

    public int getPlaneId() {
        return planeId;
    }
    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }
}
