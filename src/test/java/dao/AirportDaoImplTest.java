package dao;

import entities.Airport;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class AirportDaoImplTest {
    @Test
    public void testGetAllAirports() {
        Set<Airport> airports = new AirportDaoImpl().getAllAirports();
        assertTrue(airports.contains(new Airport(1, "Berlin")));
        assertTrue(airports.contains(new Airport(2, "London")));
        assertTrue(airports.contains(new Airport(3, "Paris")));
        assertTrue(airports.contains(new Airport(4, "Rome")));
    }

    @Test
    public void testGetAirportById() {
        AirportDaoImpl airportDao = new AirportDaoImpl();
        assertEquals(new Airport(1, "Berlin"), airportDao.getAirportById(1));
        assertEquals(new Airport(2, "London"), airportDao.getAirportById(2));
        assertEquals(new Airport(3, "Paris"), airportDao.getAirportById(3));
        assertEquals(new Airport(4, "Rome"), airportDao.getAirportById(4));
    }
}
