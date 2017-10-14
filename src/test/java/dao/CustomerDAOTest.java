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
                    connection.prepareStatement("INSERT INTO Customer (FirstName, LastName, PersonID) VALUES(?, ?, ?)");
            preparedStatement.setString(1, "NAME_ENTRY#" + i);
            preparedStatement.setString(2, "LASTNAME_ENTRY#" + i);
            preparedStatement.setString(3, "PERSONID_ENTRY#" + i);
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
        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerFirstName("FIRSTNAME_INSERT");
        customer.setCustomerLastName("LASTNAME_INSERT");
        customer.setCustomerPersonId("1213123QWE");
        assertTrue(customerDAO.insert(customer));
    }

    @Test
    public void testUpdate() throws SQLException {
        DAO<Customer> customerDAO = new CustomerDAO();
        Set<Customer> customers = customerDAO.getAll();
        Customer customer = new Customer();
        customer.setCustomerId(customers.iterator().next().getCustomerId());
        customer.setCustomerFirstName("FIRSTNAME_INSERT");
        customer.setCustomerLastName("LASTNAME_INSERT");
        customer.setCustomerPersonId("1213123QWE");
        assertTrue(customerDAO.update(customer));
    }

    @Test
    public void testDelete() throws SQLException {
        DAO<Customer> customerDAO = new CustomerDAO();
        Set<Customer> customers = customerDAO.getAll();
        Customer customer = new Customer();
        customer.setCustomerId(customers.iterator().next().getCustomerId());
        assertTrue(customerDAO.delete(customer));
    }

    @AfterClass
    public static void cleanDatabase() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        for (int i = 0; i < NUMBER_OF_ENTRIES; i++) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Customer WHERE FirstName=? AND LastName=? AND PersonID=?");
            preparedStatement.setString(1, "NAME_ENTRY#" + i);
            preparedStatement.setString(2, "LASTNAME_ENTRY#" + i);
            preparedStatement.setString(3, "PERSONID_ENTRY#" + i);
            preparedStatement.executeUpdate();
        }
    }
}
