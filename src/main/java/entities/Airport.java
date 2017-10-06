package entities;

public class Airport {
    private int airportId;
    private String Name;

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
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
                "airportId=" + airportId +
                ", Name='" + Name + '\'' +
                '}';
    }
}
