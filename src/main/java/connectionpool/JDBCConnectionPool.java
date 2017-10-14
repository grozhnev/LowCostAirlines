package connectionpool;

import java.util.*;
import java.sql.*;

public class JDBCConnectionPool implements Runnable {
    private int initialConnections = 5;
    private Vector connectionsAvailable = new Vector();
    private Vector connectionsUsed = new Vector();

    private String connectionUrl;
    private String userName;
    private String userPassword;
    public JDBCConnectionPool() throws SQLException {
        try {
            this.connectionUrl = "jdbc:mysql://localhost:3306/ticketsystem";
            this.userName = "testuser";
            this.userPassword = "testpassword";
            Class.forName("com.mysql.jdbc.Driver");
            for (int count = 0; count < initialConnections; count++) {
                connectionsAvailable.addElement(getConnection());
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, userName,
                userPassword);
    }
    public synchronized Connection connectionCheck() throws SQLException {
        Connection newConnection = null;
        if (connectionsAvailable.size() == 0) {
            newConnection = getConnection();
            connectionsUsed.addElement(newConnection);
        } else {
            newConnection = (Connection) connectionsAvailable.lastElement();
            connectionsAvailable.removeElement(newConnection);
            connectionsUsed.addElement(newConnection);
        }
        return newConnection;
    }

    public int availableCount() {
        return connectionsAvailable.size();
    }
    public void run() {
        try {
            while (true) {
                synchronized (this) {
                    while (connectionsAvailable.size() > initialConnections) {
                        Connection connection = (Connection) connectionsAvailable
                                .lastElement();
                        connectionsAvailable.removeElement(connection);

                        connection.close();
                    }

                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}