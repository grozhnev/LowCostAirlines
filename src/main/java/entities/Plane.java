package entities;

public class Plane {
    /*
    CREATE TABLE Plane
    (
       id              INT AUTO_INCREMENT,
       maxLoad         INT,
       currentLoad     INT,
       PRIMARY KEY (id)
    );*/

    private int id;
    private int maxLoad;
    private int currentLoad;

    public Plane() {}

    public Plane(int id, int maxLoad, int currentLoad) {
        this.id = id;
        this.maxLoad = maxLoad;
        this.currentLoad = currentLoad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "planeId=" + id +
                ", maxLoad=" + maxLoad +
                ", currentLoad=" + currentLoad +
                '}';
    }
}
