package dao;

import org.junit.*;
import entities.Plane;
import services.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import static org.junit.Assert.*;

public class PlaneDAOTest {
    private static final int NUMBER_OF_ENTRIES = 10;

    @BeforeClass
    public static void setupDatabase() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        for (int i = 0; i < NUMBER_OF_ENTRIES; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Plane (MaxLoad, CurrentLoad) VALUES (50, 10)");
            preparedStatement.executeUpdate();
        }
    }

    @Test
    public void testGetAll() throws SQLException {
        PlaneDAO planeDAO = new PlaneDAO();
        assertNotNull(planeDAO.getAll());
    }

    @Test
    public void testGetById() throws SQLException {
        PlaneDAO planeDAO = new PlaneDAO();
        Set<Plane> planes = planeDAO.getAll();
        for (Plane plane:
             planes) {
            assertNotNull(planeDAO.getById(plane.getPlaneId()));
        }
    }

    @Test
    public void testInsert() throws SQLException {
        PlaneDAO planeDAO = new PlaneDAO();
        Plane plane = new Plane();
        plane.setPlaneId(1);
        plane.setMaxLoad(123);
        plane.setCurrentLoad(31);
        assertTrue(planeDAO.insert(plane));
    }

    @Test
    public void testUpdate() throws SQLException {
        PlaneDAO planeDAO = new PlaneDAO();
        Set<Plane> planes = planeDAO.getAll();
        Plane plane = new Plane();
        plane.setPlaneId(planes.iterator().next().getPlaneId());
        plane.setMaxLoad(123);
        plane.setCurrentLoad(31);
        assertTrue(planeDAO.update(plane));
    }

    @Test
    public void testDelete() throws SQLException {
        PlaneDAO planeDAO = new PlaneDAO();
        Set<Plane> planes = planeDAO.getAll();
        Plane plane = new Plane();
        plane.setPlaneId(planes.iterator().next().getPlaneId());
        plane.setMaxLoad(123);
        plane.setCurrentLoad(31);
        assertTrue(planeDAO.delete(plane));
    }

    @AfterClass
    public static void cleanDatabase() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        for (int i = 0; i < NUMBER_OF_ENTRIES; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Plane WHERE MaxLoad=50 AND CurrentLoad=10");
            preparedStatement.executeUpdate();
        }
    }
}
