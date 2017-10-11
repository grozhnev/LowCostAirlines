package dao;

import entities.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import services.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TicketDAOTest {
    private static final int NUMBER_OF_ENTRIES = 10;

    @BeforeClass
    public static void setupDatabase() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        for (int i = 1; i <= NUMBER_OF_ENTRIES; i++) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Ticket (idFlight, idCustomer, Price, LuggagePrice, RegistrationPriority) VALUES (?, ?, ?, ?, ?)");
            Set<Flight> flights = new FlightDAO().getAll();
            preparedStatement.setInt(1, flights.iterator().next().getFlightId());
            Set<Customer> customers = new CustomerDAO().getAll();
            preparedStatement.setInt(2, customers.iterator().next().getCustomerId());
            preparedStatement.setInt(3, i + 333);
            preparedStatement.setInt(4, i + 444);
            preparedStatement.setBoolean(5, true);
        }
    }

    @Test
    public void testGetAll() throws SQLException {
        DAO<Ticket> ticketDAO = new TicketDAO();
        assertNotNull(ticketDAO.getAll());
    }

    @Test
    public void testGetById() throws SQLException {
        DAO<Ticket> ticketDAO = new TicketDAO();
        Set<Ticket> tickets = ticketDAO.getAll();
        for (Ticket ticket:
                tickets) {
            assertNotNull(ticketDAO.getById(ticket.getTicketId()));
        }
    }

    @Test
    public void testInsert() throws SQLException {
        DAO<Ticket> ticketDAO = new TicketDAO();
        Ticket ticket = new Ticket();
        ticket.setFlightId(3);
        ticket.setCustomerId(2);
        ticket.setPrice(30);
        ticket.setLuggagePrice(20);
        ticket.setRegistrationPriority(true);
        assertTrue(ticketDAO.insert(ticket));
    }

    @Test
    public void testUpdate() throws SQLException {
        DAO<Ticket> ticketDAO = new TicketDAO();
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketDAO.getAll().iterator().next().getTicketId());
        ticket.setFlightId(3);
        ticket.setCustomerId(2);
        ticket.setPrice(30);
        ticket.setLuggagePrice(20);
        ticket.setRegistrationPriority(true);
        assertTrue(ticketDAO.update(ticket));
    }

    @Test
    public void testDelete() throws SQLException {
        DAO<Ticket> ticketDAO = new TicketDAO();
        Set<Ticket> tickets = ticketDAO.getAll();
        Ticket ticket = new Ticket();
        ticket.setTicketId(tickets.iterator().next().getTicketId());
        assertTrue(ticketDAO.delete(ticket));
    }

    @AfterClass
    public static void cleanDatabase() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        for (int i = 1; i <= NUMBER_OF_ENTRIES; i++) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Ticket WHERE Price=? AND LuggagePrice=? AND RegistrationPriority=?");
            preparedStatement.setInt(1, i + 333);
            preparedStatement.setInt(2, i + 444);
            preparedStatement.setBoolean(3, true);
            preparedStatement.executeUpdate();
        }
    }
}
