package dao;

import entities.Airport;
import org.junit.*;

import static org.junit.Assert.*;
import services.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class AirportDAOTest {
    private static ArrayList<String> testEntries = new ArrayList<>();
    private static final int NUMBER_OF_ENTRIES = 10;

    @BeforeClass
    public static void setupDatabase() throws SQLException {
        for (int i = 0; i < NUMBER_OF_ENTRIES; i++) {
            testEntries.add("ENTRY#" + i);
        }
        Connection connection = ConnectionFactory.getConnection();
        for (String testEntry:
                testEntries) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Airport (Name) VALUES (?)");
            preparedStatement.setString(1, testEntry);
            preparedStatement.executeUpdate();
        }
    }

    @Test
    public void testGetAll() throws SQLException {
        DAO<Airport> airportDAO = new AirportDAO();
        assertNotNull(airportDAO.getAll());
    }

    @Test
    public void testGetById() throws SQLException {
        DAO<Airport> airportDAO = new AirportDAO();
        Set<Airport> airports = airportDAO.getAll();
        int firstId = 1;

        for (Airport airport:
                airports) {
            if (airport.getName().equals(testEntries.get(0))) {
                firstId = airport.getAirportId();
            }
        }

        for (int i = firstId; i < firstId + NUMBER_OF_ENTRIES; i++) {
            assertTrue(testEntries.contains(airportDAO.getById(i).getName()));
        }
    }

    @Test
    public void testInsert() throws SQLException {
        DAO<Airport> airportDAO = new AirportDAO();
        Airport airport = new Airport();
        airport.setAirportId(0);
        airport.setName("TEST_AIRPORT");
        assertTrue(airportDAO.insert(airport));
    }

    @Test
    public void testUpdate() throws SQLException {
        DAO<Airport> airportDAO = new AirportDAO();
        Airport airport = new Airport();
        airport.setAirportId(1);
        airport.setName("TEST_AIRPORT_UPDATE");
        assertTrue(airportDAO.update(airport));
    }

    @Test
    public void testDelete() throws SQLException {
        DAO<Airport> airportDAO = new AirportDAO();
        Set<Airport> airports = airportDAO.getAll();
        Airport airport = new Airport();
        airport.setAirportId(airports.iterator().next().getAirportId());
        assertTrue(airportDAO.delete(airport));
    }

    @AfterClass
    public static void cleanDatabase() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        for (String testEntry:
                testEntries) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Airport WHERE Name=?");
            preparedStatement.setString(1, testEntry);
            preparedStatement.executeUpdate();
        }
    }
}
