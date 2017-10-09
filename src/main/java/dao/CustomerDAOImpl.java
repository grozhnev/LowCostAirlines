package dao;

import entities.Customer;
import services.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CustomerDAOImpl implements DAO<Customer>{

    private Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("idCustomer"));
        customer.setFirstName(rs.getString("Name"));
        customer.setLastName(rs.getString("LastName"));
        customer.setPersonId(rs.getString("PersonID"));
        return customer;
    }

    @Override
    public Set<Customer> getAll() throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM customer");
        Set<Customer> customers = new HashSet<>();
        while(rs.next())
        {
            Customer customer = extractCustomerFromResultSet(rs);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public Customer getById(int id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM customer WHERE idCustomer=" + id);
        if(rs.next())
        {
            Customer customer = extractCustomerFromResultSet(rs);
            return customer;
        }
        return null;
    }

    @Override
    public boolean insert(Customer customer) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement ps = connection.prepareStatement("INSERT INTO customer VALUES (?, ?, ?, ?)");
        ps.setInt(1, customer.getId());
        ps.setString(2, customer.getFirstName());
        ps.setString(3, customer.getLastName());
        ps.setString(4, customer.getPersonId());

        int i = ps.executeUpdate();
        if(i == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Customer customer) throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE customer " +
                "SET Name=?, LastName=?, PersonID=?" +
                " WHERE idCustomer=?");

        ps.setInt(1, customer.getId());
        ps.setString(2, customer.getFirstName());
        ps.setString(3, customer.getLastName());
        ps.setString(4, customer.getPersonId());
         int i = ps.executeUpdate();
        if(i == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Customer customer) throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
        Statement stmt = connection.createStatement();
        int i = stmt.executeUpdate("DELETE FROM customer " +
                "WHERE idCustomer=" + customer.getId());
        if(i == 1) {
            return true;
        }
        return false;
    }
}
