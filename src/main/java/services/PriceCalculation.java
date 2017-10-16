package services;

import lombok.Data;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Data
public class PriceCalculation {
    private static Long basicPrice = 100L;
    private static Long basicLuggageFee = 50L;
    private static Long approachingDepartureDailyFee = 35L;
    private static Long luggagePricePerKilo = 5L;

    private Long price;
    private Long timeBeforeDeparture;
    private Long daysBeforeDeparture;

    public Long calculatePrice(Long inputPrice, Date departureDate, Boolean priority,
                               Double planeLoadRate, Long luggageWeight) throws SQLException {

        Long ticketPrice = inputPrice;

        Date currentDate = new Timestamp(System.currentTimeMillis());

        timeBeforeDeparture = departureDate.getTime() - currentDate.getTime();

        if (timeBeforeDeparture <= 0L) {
            System.err.println("Departure time you've chosen has already passed! \nPlease, select valid date.");
            throw new IllegalArgumentException();
        } else {
            daysBeforeDeparture = TimeUnit.MILLISECONDS.toDays(timeBeforeDeparture);
            if (priority) {
                ticketPrice *= 2;
            }
            if (luggageWeight >= 0L) {
                ticketPrice += luggagePricePerKilo * luggageWeight + basicLuggageFee;
            }
            if (daysBeforeDeparture <= 14L) {
                ticketPrice += daysBeforeDeparture * approachingDepartureDailyFee;
            }
            ticketPrice *= Math.round(planeLoadRate * 100) + basicPrice;
        }
        return ticketPrice;
    }

}