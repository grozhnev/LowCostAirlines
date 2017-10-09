package entities;

/**
 * Airport entity implementation
 * @author klysov
 */
public class Airport {
    private int airportId;
    private String Name;

    public Airport() {
    }

    public Airport(int idAirport, String name) {
        this.airportId = idAirport;
        Name = name;
    }

    public int getIdAirport() {
        return airportId;
    }

    public void setIdAirport(int idAirport) {
        this.airportId = idAirport;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;

        Airport airport = (Airport) o;

        if (airportId != airport.airportId) return false;
        return getName().equals(airport.getName());
    }

    @Override
    public int hashCode() {
        int result = airportId;
        result = 31 * result + getName().hashCode();
        return result;
    }
}
