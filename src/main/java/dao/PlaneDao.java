package dao;

import entities.Plane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * DAO interface for plane entity
 * @author klysov
 */
public interface PlaneDao {
    Plane extractPlaneFromResultSet(ResultSet rs) throws SQLException;
    Set<Plane> getAllPlanes();
    Plane getPlaneById(int id);
    boolean insertPlane(Plane plane);
    boolean updatePlane(Plane plane);
    boolean deletePlane(Plane plane);
}
