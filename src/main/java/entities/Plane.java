package entities;

public class Plane {
    private int planeId;
    private int maxLoad;
    private int currentLoad;

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
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
                "planeId=" + planeId +
                ", maxLoad=" + maxLoad +
                ", currentLoad=" + currentLoad +
                '}';
    }
}
