package entities;

/**
 * Plane entity implementation
 * @author klysov
 */
public class Plane {
    private int idPlane;
    private int maxLoad;
    private int currentLoad;

    public Plane() {}

    public Plane(int idPlane, int maxLoad, int currentLoad) {
        this.idPlane = idPlane;
        this.maxLoad = maxLoad;
        this.currentLoad = currentLoad;
    }

    public int getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }

    public int getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(int maxLoad) {
        this.maxLoad = maxLoad;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "planeId=" + idPlane +
                ", maxLoad=" + maxLoad +
                ", currentLoad=" + currentLoad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;

        Plane plane = (Plane) o;

        if (getIdPlane() != plane.getIdPlane()) return false;
        if (getMaxLoad() != plane.getMaxLoad()) return false;
        return getCurrentLoad() == plane.getCurrentLoad();
    }

    @Override
    public int hashCode() {
        int result = getIdPlane();
        result = 31 * result + getMaxLoad();
        result = 31 * result + getCurrentLoad();
        return result;
    }
}
