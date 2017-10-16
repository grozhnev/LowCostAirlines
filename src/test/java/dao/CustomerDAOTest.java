package dao;

import entities.Customer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import services.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CustomerDAOTest {
    private static final int NUMBER_OF_ENTRIES = 10;

    @BeforeClass
    public static void setupDatabase() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        for (int i = 0; i < NUMBER_OF_ENTRIES; i++) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Customer (FirstName, LastName, Passport, Email, Passwd) VALUES(?, ?, ?, ?, ?)");
            preparedStatement.setString(1, "NAME_ENTRY#" + i);
            preparedStatement.setString(2, "LASTNAME_ENTRY#" + i);
            preparedStatement.setString(3, "PASSPORT_ENTRY#" + i);
            preparedStatement.setString(4, "EMAIL_ENTRY#" + i);
            preparedStatement.setString(5, "PASSWD_ENTRY#" + i);
            preparedStatement.executeUpdate();
        }
    }

    @Test
    public void testGetAll() throws SQLException {
        DAO<Customer> customerDAO = new CustomerDAO();
        assertNotNull(customerDAO.getAll());
    }

    @Test
    public void testGetById() throws SQLException {
        DAO<Customer> customerDAO = new CustomerDAO();
        Set<Customer> customers = customerDAO.getAll();
        for (Customer customer:
                customers) {
            assertNotNull(customerDAO.getById(customer.getCustomerId()));
        }
    }

    @Test
    public void testInsert() throws SQLException {
        DAO<Customer> customerDAO = new CustomerDAO();
        Customer customer = new Customer()
                .setFirstName("FIRSTNAME_INSERT")
                .setLastName("LASTNAME_INSERT")
                .setPassport("123-123-123")
                .setEmail("INSERT_EMAIL")
                .setPassword("INSERT_PASSWD");
        assertTrue(customerDAO.insert(customer));
    }

    @Test
    public void testUpdate() throws SQLException {
        DAO<Customer> customerDAO = new CustomerDAO();
        Set<Customer> customers = customerDAO.getAll();
        Customer customer = new Customer()
                .setCustomerId(customers.iterator().next().getCustomerId())
                .setFirstName("UPDATE_FIRSTNAME")
                .setLastName("UPDATE_LASTNAME")
                .setPassport("132-132-132")
                .setEmail("UPDATE_MAIL")
                .setPassword("UPDATE_PASSWD");
        assertTrue(customerDAO.update(customer));
    }

    @Test
    public void testDelete() throws SQLException {
        DAO<Customer> customerDAO = new CustomerDAO();
        Set<Customer> customers = customerDAO.getAll();
        Customer customer = new Customer()
                .setCustomerId(customers.iterator().next().getCustomerId());
        assertTrue(customerDAO.delete(customer));
    }

    @AfterClass
    public static void cleanDatabase() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        for (int i = 0; i < NUMBER_OF_ENTRIES; i++) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Customer WHERE Email=?");
            preparedStatement.setString(1, "EMAIL_ENTRY#" + i);
            preparedStatement.executeUpdate();
        }
    }
}
