package dao;

import entities.Ticket;

import java.sql.SQLException;
import java.util.Set;

public class TicketDAO implements DAO<Ticket> {
    @Override
    public Set<Ticket> getAll() throws SQLException {
        return null;
    }

    @Override
    public Ticket getById(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean insert(Ticket ticket) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Ticket ticket) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Ticket ticket) throws SQLException {
        return false;
    }
}
