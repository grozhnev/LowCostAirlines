package dao;

import entities.Airport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * DAO interface for airport entity
 * @author klysov
 */
public interface AirportDao {
    Airport extractAirportFromResultSet(ResultSet rs) throws SQLException;
    Set<Airport> getAllAirports();
    Airport getAirportById(int id);
    boolean insertAirport(Airport airport);
    boolean updateAirport(Airport airport);
    boolean deleteAirport(Airport airport);
}
