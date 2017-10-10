package services;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connection implementation
 * @author klysov
 */
public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/ticketsystem";
    private static final String USER = "testuser";
    private static final String PASS = "testpassword";

    /**
     * Get a connection to database
     * @return Connection object
     */
    public static Connection getConnection()
    {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}