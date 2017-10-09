package dao;

import entities.Ticket;
import services.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TicketDaoImpl implements DAO<Ticket>{

    private Ticket extractTicketFromResultSet(ResultSet rs) throws SQLException{
        Ticket ticket = new Ticket();
        ticket.setCustomerId(rs.getInt("idCustomer"));
        ticket.setFlightId(rs.getInt("idFlight"));
        ticket.setTicketId(rs.getInt("inTicket"));
        ticket.setLuggagePrice(rs.getInt("luggagePrice"));
        ticket.setPrice(rs.getLong("Price"));
        ticket.setRegistrationPriority(rs.getBoolean("RegistrationPriority"));
        return ticket;
    }

    @Override
    public Set<Ticket> getAll() throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ticket");
        Set<Ticket> tickets = new HashSet<>();
        while(rs.next()){
            Ticket ticket = extractTicketFromResultSet(rs);
            tickets.add(ticket);
        }
        return tickets;
    }

    @Override
    public Ticket getById(int id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ticket where idTicket" + id);
        if (rs.next()) {

            Ticket ticket = extractTicketFromResultSet(rs);
            return ticket;
        }
        return null;
    }

     @Override
     public boolean insert(Ticket ticket) throws SQLException {
         Connection connection = ConnectionFactory.getConnection();
         PreparedStatement ps = connection.prepareStatement("INSERT INTO ticket VALUES (?,?,?,?,?,?)");
         ps.setInt(1, ticket.getCustomerId());
         ps.setInt(2, ticket.getFlightId());
         ps.setInt(3, ticket.getTicketId());
         ps.setInt(4, ticket.getLuggagePrice());
         ps.setLong(5, ticket.getPrice());
         ps.setBoolean(6, ticket.isRegistrationPriority());

         int i = ps.executeUpdate();
             if (i == 1) {
                 return true;
             }
             return false;
         }

     @Override
     public boolean update(Ticket ticket) throws SQLException{
        Connection connection  = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE ticket " +
        "SET idFlight=?, idCustomer=?, RegistrationPriority=? " +
                "WHERE Price=?, idTicket=?, LuggagePrice=?");

        ps.setInt(1, ticket.getFlightId());
        ps.setInt(2, ticket.getCustomerId());
        ps.setInt(3, ticket.getTicketId());
        ps.setLong(4, ticket.getPrice());
        ps.setBoolean(5,ticket.isRegistrationPriority());
        ps.setInt(6,ticket.getLuggagePrice());

         int i = ps.executeUpdate();
         if (i == 1) {
             return true;
         }
         return false;
     }

     @Override
     public boolean delete(Ticket ticket) throws SQLException{
         Connection connection = ConnectionFactory.getConnection();
         Statement stmt = connection.createStatement();
         int i = stmt.executeUpdate("DELETE FROM ticket" +
                 "WHERE idCustomer=" + ticket.getCustomerId());

         if(i == 1) {
             return true;
         }
         return false;
     }
}
