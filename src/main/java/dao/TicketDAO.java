package dao;

import connectionpool.JDBCConnectionPool;
import entities.Ticket;
import org.apache.log4j.Logger;
import services.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TicketDAO implements DAO<Ticket> {

    static final Logger LOGGER = Logger.getLogger(TicketDAO.class);

    private Ticket extractTicketFromResultSet(ResultSet resultSet) throws SQLException {
        return new Ticket()
                .setTicketId(resultSet.getInt("TicketID"))
                .setFlightId(resultSet.getInt("FlightID"))
                .setCustomerId(resultSet.getInt("CustomerID"))
                .setPrice(resultSet.getInt("Price") )
                .setLuggagePrice(resultSet.getInt("LuggagePrice"))
                .setRegistrationPriority(resultSet.getBoolean("RegistrationPriority"));
    }

    @Override
    public Set<Ticket> getAll() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Ticket");
        Set<Ticket> tickets = new HashSet<>();
        while(resultSet.next()) {
            Ticket ticket = extractTicketFromResultSet(resultSet);
            tickets.add(ticket);
        }

        if (!tickets.isEmpty()) {
            return tickets;
        }

        LOGGER.warn("No tickets has been found.");
        throw new SQLException("No tickets has been found.");
    }

    @Override
    public Ticket getById(int id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Ticket WHERE TicketID=" + id);

        if(resultSet.next()) {
            return extractTicketFromResultSet(resultSet);
        }

        LOGGER.warn("No ticket has been found.");
        throw new SQLException("No ticket has been found.");
    }

    @Override
    public boolean insert(Ticket ticket) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Ticket (FlightID, CustomerID, Price, LuggagePrice, RegistrationPriority) VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, ticket.getFlightId());
        preparedStatement.setInt(2, ticket.getCustomerId());
        preparedStatement.setInt(3, ticket.getPrice());
        preparedStatement.setInt(4, ticket.getLuggagePrice());
        preparedStatement.setBoolean(5,ticket.isRegistrationPriority());
        int i = preparedStatement.executeUpdate();
        return i == 1;
    }

    @Override
    public boolean update(Ticket ticket) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Ticket SET CustomerID=?, Price=?, LuggagePrice=?, RegistrationPriority=? WHERE TicketID=?");
        preparedStatement.setInt(1, ticket.getCustomerId());
        preparedStatement.setInt(2, ticket.getPrice());
        preparedStatement.setInt(3, ticket.getLuggagePrice());
        preparedStatement.setBoolean(4, ticket.isRegistrationPriority());
        preparedStatement.setInt(5, ticket.getTicketId());
        int i = preparedStatement.executeUpdate();
        return i == 1;
    }

    @Override
    public boolean delete(Ticket ticket) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate("DELETE FROM Ticket WHERE TicketID=" + ticket.getTicketId());
        return i == 1;
    }
}
