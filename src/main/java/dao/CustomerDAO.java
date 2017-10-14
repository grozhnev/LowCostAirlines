package dao;

import entities.Customer;
import services.ConnectionFactory;
import connectionpool.JDBCConnectionPool;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CustomerDAO implements DAO<Customer>{

    private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(resultSet.getInt("idCustomer"));
        customer.setCustomerFirstName(resultSet.getString("FirstName"));
        customer.setCustomerLastName(resultSet.getString("LastName"));
        customer.setCustomerPersonId(resultSet.getString("PersonID"));
        return customer;
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

        throw new SQLException("No customers found.");
    }

    @Override
    public Customer getById(int id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer WHERE idCustomer=" + id);

        if(resultSet.next()) {
            Customer customer = extractCustomerFromResultSet(resultSet);
            return customer;
        }

        throw new SQLException("No customer found.");
    }

    @Override
    public boolean insert(Customer customer) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Customer (FirstName, LastName, PersonID) VALUES (?, ?, ?)");
        preparedStatement.setString(1, customer.getCustomerFirstName());
        preparedStatement.setString(2, customer.getCustomerLastName());
        preparedStatement.setString(3, customer.getCustomerPersonId());
        int i = preparedStatement.executeUpdate();
        return i == 1;
    }

    @Override
    public boolean update(Customer customer) throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Customer SET FirstName=?, LastName=?, PersonID=? WHERE idCustomer=?");
        preparedStatement.setString(1, customer.getCustomerFirstName());
        preparedStatement.setString(2, customer.getCustomerLastName());
        preparedStatement.setString(3, customer.getCustomerPersonId());
        preparedStatement.setInt(4, customer.getCustomerId());
        int i = preparedStatement.executeUpdate();
        return i == 1;
    }

    @Override
    public boolean delete(Customer customer) throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate("DELETE FROM Customer WHERE idCustomer=" + customer.getCustomerId());
        return i == 1;
    }
}
