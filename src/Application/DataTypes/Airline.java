package Application.DataTypes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Airline {

    private IntegerProperty airlineID;
    private StringProperty departureCity, arrivalCity;

    public Airline() {
        airlineID = new SimpleIntegerProperty();
        departureCity = new SimpleStringProperty("");
        arrivalCity = new SimpleStringProperty("");
    }

    public Airline(int airlineID, String departureCity, String arrivalCity){
        this.airlineID = new SimpleIntegerProperty(airlineID);
        this.departureCity = new SimpleStringProperty(departureCity);
        this.arrivalCity = new SimpleStringProperty(arrivalCity);
    }

    public int getAirlineID(){
        return airlineID.get();
    }

    public void setAirlineID(int airlineID){
        this.airlineID.set(airlineID);
    }

    public String getDepartureCity() {
        return departureCity.get();
    }

    public void setDepartureCity(String departureCity){
        this.departureCity.set(departureCity);
    }

    public String getArrivalCity() {
        return arrivalCity.get();
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity.set(arrivalCity);
    }

    @Override
    public String toString() {
        return "Airline{" +
                "airlineID=" + airlineID +
                ", departureCity=" + departureCity +
                ", arrivalCity=" + arrivalCity +
                '}';
    }
}
