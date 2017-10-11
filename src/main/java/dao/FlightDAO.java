package dao;

import entities.Flight;
import services.ConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class FlightDAO implements DAO<Flight> {

    private Flight extractFlightFromResultSet(ResultSet resultSet) throws SQLException {
        Flight flight = new Flight();
        flight.setFlightId( resultSet.getInt("idFlight") );
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        flight.setDateTime(LocalDateTime.from(dateTimeFormatter.parse(resultSet.getString("DateTime").replaceAll("\\.0",""))));
        flight.setAirportSource( resultSet.getInt("Airport_Source") );
        flight.setAirportDestination(resultSet.getInt("Airport_Destination"));
        flight.setPlaneId(resultSet.getInt("idPlane"));
        return flight;
    }

    @Override
    public Set<Flight> getAll() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Flight");
        Set<Flight> flights = new HashSet<>();
        while(resultSet.next()) {
            Flight flight = extractFlightFromResultSet(resultSet);
            flights.add(flight);
        }

        if (!flights.isEmpty()) {
            return flights;
        }

        throw new SQLException("No flights has been found.");
    }

    @Override
    public Flight getById(int id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Flight WHERE idFlight=" + id);

        if(resultSet.next()) {
            return extractFlightFromResultSet(resultSet);
        }

        throw new SQLException("No flight has been found.");
    }

    @Override
    public boolean insert(Flight flight) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Flight (DateTime, Airport_Source, Airport_Destination, idPlane) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, String.valueOf(flight.getDateTime()));
        preparedStatement.setInt(2, flight.getAirportSource());
        preparedStatement.setInt(3, flight.getAirportDestination());
        preparedStatement.setInt(4, flight.getPlaneId());
        int i = preparedStatement.executeUpdate();
        return i == 1;
    }

    @Override
    public boolean update(Flight flight) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Flight SET DateTime=?, Airport_Source=?, Airport_Destination=?, idPlane=? WHERE idFlight=?");
        preparedStatement.setString(1, String.valueOf(flight.getDateTime()));
        preparedStatement.setInt(2, flight.getAirportSource());
        preparedStatement.setInt(3, flight.getAirportDestination());
        preparedStatement.setInt(4, flight.getPlaneId());
        preparedStatement.setInt(5, flight.getFlightId());
        int i = preparedStatement.executeUpdate();
        return i == 1;
    }

    @Override
    public boolean delete(Flight flight) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate("DELETE FROM Flight WHERE idFlight=" + flight.getFlightId());
        return i == 1;
    }
}
