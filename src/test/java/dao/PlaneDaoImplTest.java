package dao;

import entities.Plane;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class PlaneDaoImplTest {
    @Test
    public void testGetAllAirports() {
        Set<Plane> planes = new PlaneDaoImpl().getAllPlanes();
        assertTrue(planes.contains(new Plane(1, 50,0)));
        assertTrue(planes.contains(new Plane(2, 50,0)));
        assertTrue(planes.contains(new Plane(3, 50,0)));
    }

    @Test
    public void testGetAirportById() {
        PlaneDaoImpl planeDao = new PlaneDaoImpl();
        assertEquals(new Plane(1, 50,0), planeDao.getPlaneById(1));
        assertEquals(new Plane(2, 50,0), planeDao.getPlaneById(2));
        assertEquals(new Plane(3, 50,0), planeDao.getPlaneById(3));
    }
}
