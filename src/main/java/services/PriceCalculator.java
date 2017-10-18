package services;


import entities.Flight;

public class PriceCalculator {
    private final static Long BASIC_PRICE = 100L;
    private final static Long BASIC_LUGGAGE_FEE = 50L;
    private final static Long APPROACHING_DEPARTURE_DAILY_FEE = 35L;
    private final static Long LUGGAGE_PRICE_PER_KILO = 5L;

    private int price;

    public int calculatePrice(Flight flight, boolean priority, int weight){



        return price;
    }
}
