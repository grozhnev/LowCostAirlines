package services;


import dao.PlaneDAO;
import entities.Flight;
import entities.Plane;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class PriceCalculator {
    private final static int BASIC_PRICE = 100;
    private final static int BASIC_LUGGAGE_FEE = 50;
    private final static int APPROACHING_DEPARTURE_DAILY_FEE = 35;
    private final static int LUGGAGE_PRICE_PER_KILO = 5;

    private LocalDateTime currentTime;
    private LocalDateTime departureTime;

    private int daysToDeparture;
    private int price;

    private Plane currentFlightPlane;

    public int calculatePrice(Flight flight, boolean priority, int weight){
        try {
        currentTime=LocalDateTime.now();
        departureTime = flight.getDateTime();
        daysToDeparture = (departureTime.getHour() - currentTime.getHour()) * 24;
            
        currentFlightPlane = new PlaneDAO().getById(flight.getPlaneId());

        if (daysToDeparture < 0) {
            System.err.println("Departure time you've chosen has already passed! \nPlease, select valid date.");
            throw new IllegalArgumentException();
        } else {
            price = BASIC_PRICE;

            if (priority) {
                price *= 2;
            }
            if (weight >= 0) {
                price += LUGGAGE_PRICE_PER_KILO * weight + BASIC_LUGGAGE_FEE;
            }
            if (daysToDeparture <= 14) {
                price += daysToDeparture * APPROACHING_DEPARTURE_DAILY_FEE;
            }
            price *= Math.round(currentFlightPlane.getCurrentLoad() * 100);
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;

    }
}
