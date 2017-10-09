package entities;

public class Airport {
    /*
    CREATE TABLE Airport
    (
       Id       INT AUTO_INCREMENT,
       name     VARCHAR(45),
       PRIMARY KEY (Id)
    );*/
    private int id;
    private String name;

    public Airport() {
    }

    public Airport(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
