package dao;

import entities.Airport;
import services.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AirportDAO implements DAO<Airport> {

    private Airport extractAirportFromResultSet(ResultSet resultSet) throws SQLException {
        return new Airport()
                .setAirportId(resultSet.getInt("AirportID"))
                .setName(resultSet.getString("Name"));
    }

    @Override
    public Set<Airport> getAll() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Airport");
        Set<Airport> airports = new HashSet<>();

        while(resultSet.next()) {
            Airport airport = extractAirportFromResultSet(resultSet);
            airports.add(airport);
        }

        if (!airports.isEmpty()) {
            return airports;
        }

        throw new SQLException("No airports has been found.");
    }

    @Override
    public Airport getById(int id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Airport WHERE AirportID=" + id);

        if(resultSet.next()) {
            return extractAirportFromResultSet(resultSet);
        }

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
        preparedStatement.setInt(2, airport.getAirportId());
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
