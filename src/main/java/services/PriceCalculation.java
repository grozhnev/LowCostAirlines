package services;

import entities.Flight;
import entities.Plane;
import entities.Ticket;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;


public class PriceCalculation {
    private final static Long BASIC_PRICE = 100L;
    private final static Long BASIC_LUGGAGE_FEE = 50L;
    private final static Long APPROACHING_DEPARTURE_DAILY_FEE = 35L;
    private final static Long LUGGAGE_PRICE_PER_KILO = 5L;

    public void setPriceInFlights(Set<Flight> flights,
                                         Map<Flight, Ticket> ticketByFlight,
                                         Map<Ticket, Long> luggageByTicket,
                                         Map<Ticket, Boolean> priorityByTicket,
                                         Map<Flight, Plane> planesByFlight,
                                         /*Set<Ticket> setOfTickets,
                                         Set<Plane> setOfPlanes,*/
                                         Map<Plane, Double> loadByPlane) throws SQLException{
        try {

            for (Flight flight : flights) {

                Boolean ticketPriority = false;
                Long luggageWeight = 0L;
                Double loadRate = 0D;

                if (!ticketByFlight.isEmpty()) {
                    ticketPriority = priorityByTicket.get(ticketByFlight.get(flight));
                }

                if (!luggageByTicket.isEmpty()){
                    luggageWeight = luggageByTicket.get(ticketByFlight.get(flight));
                }

                if(!loadByPlane.isEmpty()){
                    loadRate = loadByPlane.get(planesByFlight.get(flight));
                }

                flight.setPrice(calculatePrice(
                        flight.getPrice(), flight.getDateTime(), ticketPriority,loadRate, luggageWeight )
                );
            }
        } catch (NullPointerException npe ){
            npe.printStackTrace();
        }
    }


    private Long calculatePrice(Long inputPrice, LocalDateTime departureDate, Boolean priority,
                               Double planeLoadRate, Long luggageWeight) {


        Long ticketPrice = inputPrice;

        /*should be LocalDateTime*/
        //Date currentDate = new Timestamp(System.currentTimeMillis());
        LocalDateTime currentDate = LocalDateTime.now();

        Long daysBeforeDeparture = (departureDate.getHour() - currentDate.getHour()) * 24L;

        if (daysBeforeDeparture < 0L) {
            System.err.println("Departure time you've chosen has already passed! \nPlease, select valid date.");
            throw new IllegalArgumentException();
        } else {
            if (priority) {
                ticketPrice *= 2;
            }
            if (luggageWeight >= 0L) {
                ticketPrice += LUGGAGE_PRICE_PER_KILO * luggageWeight + BASIC_LUGGAGE_FEE;
            }
            if (daysBeforeDeparture <= 14L) {
                ticketPrice += daysBeforeDeparture * APPROACHING_DEPARTURE_DAILY_FEE;
            }
            ticketPrice *= Math.round(planeLoadRate * 100);
        }
        return ticketPrice + BASIC_PRICE;
    }
}