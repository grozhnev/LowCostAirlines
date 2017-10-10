package dao;

import entities.Ticket;
import services.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TicketDAO implements DAO<Ticket> {

    private Ticket extractTicketFromResultSet(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setTicketId( resultSet.getInt("idTicket") );
        ticket.setFlightId( resultSet.getInt("idFlight"));
        ticket.setCustomerId( resultSet.getInt("idCustomer"));
        ticket.setPrice( resultSet.getInt("Price") );
        ticket.setLuggagePrice(resultSet.getInt("LuggagePrice"));
        ticket.setRegistrationPriority(resultSet.getBoolean("RegistrationPriority"));
        return ticket;
    }

    @Override
    public Set<Ticket> getAll() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM TIcket");
        Set<Ticket> tickets = new HashSet<>();
        while(resultSet.next()) {
            Ticket ticket = extractTicketFromResultSet(resultSet);
            tickets.add(ticket);
        }

        if (!tickets.isEmpty()) {
            return tickets;
        }

        throw new SQLException("No tickets has been found.");
    }

    @Override
    public Ticket getById(int id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Ticket WHERE idTicket=" + id);

        if(resultSet.next()) {
            return extractTicketFromResultSet(resultSet);
        }

        throw new SQLException("No ticket has been found.");
    }

    @Override
    public boolean insert(Ticket ticket) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Ticket (idFlight, idCustomer, Price, LuggagePrice, RegistrationPriority) VALUES (?, ?, ?, ?, ?)");
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
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Ticket SET idCustomer=?, Price=?, LuggagePrice=?, RegistrationPriority=? WHERE idTicket=?");
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
        int i = statement.executeUpdate("DELETE FROM Ticket WHERE idTIcket=" + ticket.getTicketId());
        return i == 1;
    }
}
