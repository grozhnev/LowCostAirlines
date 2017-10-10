package dao;

import entities.Flight;

import java.sql.SQLException;
import java.util.Set;

public class FlightDAO implements DAO<Flight> {
    @Override
    public Set<Flight> getAll() throws SQLException {
        return null;
    }

    @Override
    public Flight getById(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean insert(Flight flight) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Flight flight) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Flight flight) throws SQLException {
        return false;
    }
}
