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
        airport.setIdAirport( rs.getInt("idAirport") );
        airport.setName( rs.getString("Name") );
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM Airport WHERE idAirport=" + id);
            if(rs.next())
            {
                Airport airport = extractAirportFromResultSet(rs);
                return airport;
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
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Airport VALUES (?, ?)");
            ps.setInt(1, airport.getIdAirport());
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
            PreparedStatement ps = connection.prepareStatement("UPDATE Airport SET Name=? WHERE idAirport=?");
            ps.setString(1, airport.getName());
            ps.setInt(2, airport.getIdAirport());
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
            int i = stmt.executeUpdate("DELETE FROM Airport WHERE idAirport=" + airport.getIdAirport());
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
