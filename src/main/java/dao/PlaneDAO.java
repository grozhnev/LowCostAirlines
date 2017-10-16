package dao;

import connectionpool.ConnectionPool;
import entities.Plane;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PlaneDAO implements DAO<Plane> {

    private Plane extractPlaneFromResultSet(ResultSet resultSet) throws SQLException {
        return new Plane()
                .setPlaneId(resultSet.getInt("PlaneID"))
                .setMaxLoad(resultSet.getInt("MaxLoad"))
                .setCurrentLoad(resultSet.getInt("CurrentLoad"));
    }

    public ConnectionPool connectionThroughConnectPool = ConnectionPool.getInstance();

    @Override
    public Set<Plane> getAll() throws SQLException{
        Connection connection = connectionThroughConnectPool.takeConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Plane");
        Set<Plane> planes = new HashSet<>();
        while(resultSet.next()) {
            Plane plane = extractPlaneFromResultSet(resultSet);
            planes.add(plane);
        }

        if (!planes.isEmpty()) {
            return planes;
        }

        throw new SQLException("No planes has been found.");
    }

    @Override
    public Plane getById(int id) throws SQLException {
        Connection connection = connectionThroughConnectPool.takeConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Plane WHERE PlaneID=" + id);

        if(resultSet.next()) {
            return extractPlaneFromResultSet(resultSet);
        }

        throw new SQLException("No plane has been found.");
    }

    @Override
    public boolean insert(Plane plane) throws SQLException{
        Connection connection = connectionThroughConnectPool.takeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Plane (MaxLoad, CurrentLoad) VALUES (?, ?)");
        preparedStatement.setInt(1, plane.getMaxLoad());
        preparedStatement.setInt(2, plane.getCurrentLoad());
        int i = preparedStatement.executeUpdate();
        return i == 1;
    }

    @Override
    public boolean update(Plane plane) throws SQLException{
        Connection connection = connectionThroughConnectPool.takeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Plane SET MaxLoad=?, CurrentLoad=? WHERE PlaneID=?");
        preparedStatement.setInt(1, plane.getMaxLoad());
        preparedStatement.setInt(2, plane.getCurrentLoad());
        preparedStatement.setInt(3, plane.getPlaneId());
        int i = preparedStatement.executeUpdate();
        return i == 1;
    }

    @Override
    public boolean delete(Plane plane) throws SQLException {
        Connection connection = connectionThroughConnectPool.takeConnection();
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate("DELETE FROM Plane WHERE PlaneID=" + plane.getPlaneId());
        return i == 1;
    }
}
