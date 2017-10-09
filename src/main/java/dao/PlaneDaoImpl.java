package dao;

import entities.Plane;
import services.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of plane DAO
 * @author klysov
 */
public class PlaneDaoImpl implements PlaneDao {

    private Plane extractPlaneFromResultSet(ResultSet rs) throws SQLException {
        Plane plane = new Plane();
        plane.setIdPlane( rs.getInt("idPlane") );
        plane.setMaxLoad( rs.getInt("MaxLoad") );
        plane.setCurrentLoad( rs.getInt("CurrentLoad") );
        return plane;
    }

    @Override
    public Set<Plane> getAllPlanes() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Plane");
            Set<Plane> planes = new HashSet<>();
            while(rs.next())
            {
                Plane plane = extractPlaneFromResultSet(rs);
                planes.add(plane);
            }
            return planes;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new HashSet<>();
    }

    @Override
    public Plane getPlaneById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Plane WHERE idPlane=" + id);
            if(rs.next())
            {
                Plane plane = extractPlaneFromResultSet(rs);
                return plane;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new Plane();
    }

    @Override
    public boolean insertPlane(Plane plane) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Plane VALUES (?, ?, ?)");
            ps.setInt(1, plane.getIdPlane());
            ps.setInt(2, plane.getMaxLoad());
            ps.setInt(3, plane.getCurrentLoad());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePlane(Plane plane) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Plane SET MaxLoad=?, CurrentLoad=? WHERE idPlane=?");
            ps.setInt(1, plane.getIdPlane());
            ps.setInt(2, plane.getMaxLoad());
            ps.setInt(3, plane.getCurrentLoad());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePlane(Plane plane) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM Plane WHERE idPlane=" + plane.getIdPlane());
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
