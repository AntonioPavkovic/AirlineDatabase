package Application.DataTypes;

import javafx.beans.property.*;

public class FlightTable {

    private IntegerProperty flightID, economyLeft, coachLeft, firstClassLeft;
    private StringProperty departureDate, departureCity, arrivalCity;
    private DoubleProperty price;

    public FlightTable() {
        flightID = new SimpleIntegerProperty(0);
        economyLeft = new SimpleIntegerProperty(0);
        coachLeft = new SimpleIntegerProperty(0);
        firstClassLeft = new SimpleIntegerProperty(0);
        departureDate = new SimpleStringProperty("");
        departureCity = new SimpleStringProperty("");
        arrivalCity = new SimpleStringProperty("");
        price = new SimpleDoubleProperty(0);
    }

    public FlightTable(String departureDate) {
        this.departureDate = new SimpleStringProperty(departureDate);
        flightID = new SimpleIntegerProperty(0);
        economyLeft = new SimpleIntegerProperty(0);
        coachLeft = new SimpleIntegerProperty(0);
        firstClassLeft = new SimpleIntegerProperty(0);
        departureCity = new SimpleStringProperty("");
        arrivalCity = new SimpleStringProperty("");
        price = new SimpleDoubleProperty(0);
    }

    public FlightTable(String departureDate, String departureCity, String arrivalCity, double price,
                       int flightID, int economyLeft, int coachLeft, int firstClassLeft){
        this.departureDate = new SimpleStringProperty(departureDate);
        this.departureCity = new SimpleStringProperty(departureCity);
        this.arrivalCity = new SimpleStringProperty(arrivalCity);
        this.price = new SimpleDoubleProperty(price);
        this.flightID = new SimpleIntegerProperty(flightID);
        this.firstClassLeft = new SimpleIntegerProperty(firstClassLeft);
        this.coachLeft = new SimpleIntegerProperty(coachLeft);
        this.economyLeft = new SimpleIntegerProperty(economyLeft);
    }

    public int getFlightID() {
        return flightID.get();
    }

    public IntegerProperty flightIDProperty() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID.set(flightID);
    }

    public IntegerProperty economyLeftProperty() {
        return economyLeft;
    }

    public void setEconomyLeft(int economyLeft) {
        this.economyLeft.set(economyLeft);
    }

    public IntegerProperty coachLeftProperty() {
        return coachLeft;
    }

    public void setCoachLeft(int coachLeft) {
        this.coachLeft.set(coachLeft);
    }

    public IntegerProperty firstClassLeftProperty() {
        return firstClassLeft;
    }

    public void setFirstClassLeft(int firstClassLeft) {
        this.firstClassLeft.set(firstClassLeft);
    }

    public String getDepartureDate() {
        return departureDate.get();
    }

    public StringProperty departureDateProperty() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate.set(departureDate);
    }

    public String getDepartureCity() {
        return departureCity.get();
    }

    public StringProperty departureCityProperty() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity.set(departureCity);
    }

    public String getArrivalCity() {
        return arrivalCity.get();
    }

    public StringProperty arrivalCityProperty() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity.set(arrivalCity);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    @Override
    public String toString() {
        return "FlightTable{" +
                "flightID=" + flightID +
                ", economyLeft=" + economyLeft +
                ", coachLeft=" + coachLeft +
                ", firstClassLeft=" + firstClassLeft +
                ", departureDate=" + departureDate +
                ", departureCity=" + departureCity +
                ", arrivalCity=" + arrivalCity +
                ", price=" + price +
                '}';
    }

}
