package dao;

import entities.Airport;
import entities.Flight;
import entities.Plane;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import services.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FlightDAOTest {
    private static final int NUMBER_OF_ENTRIES = 10;

    @BeforeClass
    public static void setupDatabase() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        for (int i = 1; i <= NUMBER_OF_ENTRIES; i++) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Flight (DateTime, AirportSource, AirportDestination, PlaneID) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, "2015-" + i + "-05 11:11:11");
            Set<Airport> airports = new AirportDAO().getAll();
            preparedStatement.setLong(2, airports.iterator().next().getAirportId());
            preparedStatement.setLong(3, airports.iterator().next().getAirportId());
            Set<Plane> planes = new PlaneDAO().getAll();
            preparedStatement.setLong(4, planes.iterator().next().getPlaneId());
            preparedStatement.executeUpdate();
        }
    }

    @Test
    public void testGetAll() throws SQLException {
        DAO<Flight> flightDAO = new FlightDAO();
        assertNotNull(flightDAO.getAll());
    }

    @Test
    public void testGetById() throws SQLException {
        DAO<Flight> flightDAO = new FlightDAO();
        Set<Flight> flights = flightDAO.getAll();
        for (Flight flight:
                flights) {
            assertNotNull(flightDAO.getById(flight.getFlightId()));
        }
    }

    @Test
    public void testInsert() throws SQLException {
        DAO<Flight> flightDAO = new FlightDAO();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DAO<Airport> airportDAO = new AirportDAO();
        DAO<Plane> planeDAO = new PlaneDAO();
        Flight flight = new Flight()
                .setDateTime(LocalDateTime.from(dateTimeFormatter.parse("2015-11-05 14:29:36")))
                .setAirportSource(airportDAO.getAll().iterator().next().getAirportId())
                .setAirportDestination(airportDAO.getAll().iterator().next().getAirportId())
                .setPlaneId(planeDAO.getAll().iterator().next().getPlaneId());
        assertTrue(flightDAO.insert(flight));
    }

    @Test
    public void testUpdate() throws SQLException {
        DAO<Flight> flightDAO = new FlightDAO();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DAO<Airport> airportDAO = new AirportDAO();
        DAO<Plane> planeDAO = new PlaneDAO();
        Flight flight = new Flight()
                .setFlightId(flightDAO.getAll().iterator().next().getFlightId())
                .setDateTime(LocalDateTime.from(dateTimeFormatter.parse("2015-11-05 14:29:36")))
                .setAirportSource(airportDAO.getAll().iterator().next().getAirportId())
                .setAirportDestination(airportDAO.getAll().iterator().next().getAirportId())
                .setPlaneId(planeDAO.getAll().iterator().next().getPlaneId());
        assertTrue(flightDAO.update(flight));
    }

    @Test
    public void testDelete() throws SQLException {
        DAO<Flight> flightDAO = new FlightDAO();
        Set<Flight> flights = flightDAO.getAll();
        Flight flight = new Flight();
        flight.setFlightId(flights.iterator().next().getFlightId());
        assertTrue(flightDAO.delete(flight));
    }

    @AfterClass
    public static void cleanDatabase() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        for (int i = 1; i <= NUMBER_OF_ENTRIES; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Flight WHERE DateTime=?");
            preparedStatement.setString(1, "2015-" + i + "-05 11:11:11");
            preparedStatement.executeUpdate();
        }
    }
}
