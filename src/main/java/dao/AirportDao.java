package dao;

import entities.Airport;

import java.util.Set;

/**
 * DAO interface for airport entity
 * @author klysov
 */
public interface AirportDao {
    Set<Airport> getAllAirports();
    Airport getAirportById(int id);
    boolean insertAirport(Airport airport);
    boolean updateAirport(Airport airport);
    boolean deleteAirport(Airport airport);
}
