package dao;

import entities.Flight;
import org.apache.log4j.Logger;
import services.ConnectionFactory;
import connectionpool.JDBCConnectionPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class FlightDAO implements DAO<Flight> {

    static final Logger LOGGER = Logger.getLogger(FlightDAO.class);

    private Flight extractFlightFromResultSet(ResultSet resultSet) throws SQLException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateToParse = resultSet.getString("DateTime").replaceAll("\\.0","");

        return new Flight()
                .setFlightId(resultSet.getInt("FlightID"))
                .setDateTime(LocalDateTime.from(dateTimeFormatter.parse(dateToParse)))
                .setAirportSource(resultSet.getInt("AirportSource"))
                .setAirportDestination(resultSet.getInt("AirportDestination"))
                .setPlaneId(resultSet.getInt("PlaneID"));
    }

    @Override
    public Set<Flight> getAll() throws SQLException {
        JDBCConnectionPool connectionThroughConnectPool = new JDBCConnectionPool();
        Connection connection = connectionThroughConnectPool.getConnection();
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

        LOGGER.warn("No flights has been found.");
        throw new SQLException("No flights has been found.");
    }

    @Override
    public Flight getById(int id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Flight WHERE FlightID=" + id);

        if(resultSet.next()) {
            return extractFlightFromResultSet(resultSet);
        }

        LOGGER.warn("No flight has been found.");
        throw new SQLException("No flight has been found.");
    }

    @Override
    public boolean insert(Flight flight) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Flight (DateTime, AirportSource, AirportDestination, PlaneID) VALUES (?, ?, ?, ?)");
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
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Flight SET DateTime=?, AirportSource=?, AirportDestination=?, PlaneID=? WHERE FlightID=?");
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
        int i = statement.executeUpdate("DELETE FROM Flight WHERE FLightID=" + flight.getFlightId());
        return i == 1;
    }
}
