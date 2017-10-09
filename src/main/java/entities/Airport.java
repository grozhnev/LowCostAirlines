package entities;

public class Airport {
    /*
    CREATE TABLE Airport
    (
       Id       INT AUTO_INCREMENT,
       Name     VARCHAR(45),
       PRIMARY KEY (Id)
    );*/
    private int idAirport;
    private String Name;

    public Airport() {
    }

    public Airport(int idAirport, String name) {
        this.idAirport = idAirport;
        Name = name;
    }

    public int getIdAirport() {
        return idAirport;
    }

    public void setIdAirport(int idAirport) {
        this.idAirport = idAirport;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + idAirport +
                ", Name='" + Name + '\'' +
                '}';
    }
}
