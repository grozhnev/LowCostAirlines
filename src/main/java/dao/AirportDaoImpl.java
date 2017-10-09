package dao;

import entities.Airport;
import services.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of airport DAO
 * @author klysov
 */
public class AirportDaoImpl implements AirportDao{
    @Override
    public Airport extractAirportFromResultSet(ResultSet rs) throws SQLException {
        Airport airport = new Airport();
        airport.setId( rs.getInt("id") );
        airport.setName( rs.getString("name") );
        return airport;
    }

    @Override
    public Set<Airport> getAllAirports() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Airport");
            Set<Airport> airports = new HashSet<>();
            while(rs.next())
            {
                Airport airport = extractAirportFromResultSet(rs);
                airports.add(airport);
            }
            return airports;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Airport getAirportById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Airport WHERE Id=" + id);
            if(resultSet.next()) {
                return extractAirportFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertAirport(Airport airport) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            /*Customer actions logic is required here*/
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Airport VALUES (?, ?)");
            ps.setInt(1, airport.getId());
            ps.setString(2, airport.getName());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateAirport(Airport airport) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            /*Customer actions logic is required here*/
            PreparedStatement ps = connection.prepareStatement("UPDATE Airport SET Name=? WHERE idAirport=?");
            ps.setString(1, airport.getName());
            ps.setInt(2, airport.getId());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAirport(Airport airport) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM Airport WHERE Id=" + airport.getId());
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
