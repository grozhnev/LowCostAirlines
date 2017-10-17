package dao;

import entities.Airport;
import org.apache.log4j.Logger;
import services.ConnectionFactory;
import connectionpool.JDBCConnectionPool;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AirportDAO implements DAO<Airport> {

    private static final Logger LOGGER = Logger.getLogger(AirportDAO.class);

    private Airport extractAirportFromResultSet(ResultSet resultSet) throws SQLException {
        return new Airport()
                .setAirportId(resultSet.getLong("AirportID"))
                .setName(resultSet.getString("Name"));
    }
    @Override
    public Set<Airport> getAll() throws SQLException {

        JDBCConnectionPool connectionThroughConnectPool = new JDBCConnectionPool();
        Connection connection = connectionThroughConnectPool.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Airport");
        Set<Airport> airports = new HashSet<>();

        while(resultSet.next()) {
            Airport airport = extractAirportFromResultSet(resultSet);
            LOGGER.info("Airport=" + airport);
            airports.add(airport);
        }

        if (!airports.isEmpty()) {
            return airports;
        }

        LOGGER.warn("No airports has been found.");
        throw new SQLException("No airports has been found.");
    }

    @Override
    public Airport getById(Long id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Airport WHERE AirportID=" + id);

        if(resultSet.next()) {
            return extractAirportFromResultSet(resultSet);
        }

        LOGGER.warn("No airport has been found.");
        throw new SQLException("No airport has been found.");
    }

    @Override
    public boolean insert(Airport airport) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Airport (Name) VALUES (?)");
        preparedStatement.setString(1, airport.getName());
        int i = preparedStatement.executeUpdate();
        return i == 1;
    }

    @Override
    public boolean update(Airport airport) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Airport SET Name=? WHERE AirportID=?");
        preparedStatement.setString(1, airport.getName());
        preparedStatement.setLong(2, airport.getAirportId());
        int i = preparedStatement.executeUpdate();
        return i == 1;
    }

    @Override
    public boolean delete(Airport airport) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate("DELETE FROM Airport WHERE AirportID=" + airport.getAirportId());
        return i == 1;
    }
}
