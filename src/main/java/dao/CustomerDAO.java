package dao;

import entities.Customer;
import org.apache.log4j.Logger;
import services.ConnectionFactory;
import connectionpool.JDBCConnectionPool;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CustomerDAO implements DAO<Customer>{

    static final Logger LOGGER = Logger.getLogger(CustomerDAO.class);

    private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        return new Customer()
                .setCustomerId(resultSet.getInt("CustomerID"))
                .setFirstName(resultSet.getString("FirstName"))
                .setLastName(resultSet.getString("LastName"))
                .setPassport(resultSet.getString("Passport"))
                .setEmail(resultSet.getString("Email"))
                .setPassword(resultSet.getString("Passwd"));
    }

    @Override
    public Set<Customer> getAll() throws SQLException {

        JDBCConnectionPool connectionThroughConnectPool = new JDBCConnectionPool();
        Connection connection = connectionThroughConnectPool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer");
        Set<Customer> customers = new HashSet<>();

        while(resultSet.next()) {
            Customer customer = extractCustomerFromResultSet(resultSet);
            customers.add(customer);
        }

        if (!customers.isEmpty()) {
            return customers;
        }

        LOGGER.warn("No customers found.");
        throw new SQLException("No customers found.");
    }

    @Override
    public Customer getById(int id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer WHERE CustomerID=" + id);

        if(resultSet.next()) {
            Customer customer = extractCustomerFromResultSet(resultSet);
            LOGGER.info("Getting customer=" + customer);
            return customer;
        }

        LOGGER.warn("No customer found.");
        throw new SQLException("No customer found.");
    }

    @Override
    public boolean insert(Customer customer) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Customer (FirstName, LastName, Passport, Email, Passwd) VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.setString(3, customer.getPassport());
        preparedStatement.setString(4, customer.getEmail());
        preparedStatement.setString(5, customer.getPassword());
        int i = preparedStatement.executeUpdate();
        return i == 1;
    }

    @Override
    public boolean update(Customer customer) throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Customer SET FirstName=?, LastName=?, Passport=?, Email=?, Passwd=? WHERE CustomerID=?");
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.setString(3, customer.getPassport());
        preparedStatement.setString(4, customer.getEmail());
        preparedStatement.setString(5, customer.getPassword());
        preparedStatement.setInt(6, customer.getCustomerId());
        int i = preparedStatement.executeUpdate();
        return i == 1;
    }

    @Override
    public boolean delete(Customer customer) throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate("DELETE FROM Customer WHERE CustomerID=" + customer.getCustomerId());
        return i == 1;
    }
}
