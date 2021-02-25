package Application.DataTypes;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Flight {

    private IntegerProperty flightID, planeID, airlineID, scheduleID, firstClassLeft, coachLeft, economyLeft;
    private DoubleProperty price;

    public Flight() {
        flightID = new SimpleIntegerProperty();
        planeID = new SimpleIntegerProperty();
        airlineID = new SimpleIntegerProperty();
        scheduleID = new SimpleIntegerProperty();
        firstClassLeft = new SimpleIntegerProperty();
        coachLeft = new SimpleIntegerProperty();
        economyLeft = new SimpleIntegerProperty();
        price = new SimpleDoubleProperty();
    }
    public Flight(int flightID, int planeID, int airlineID, int scheduleID, int firstClassLeft, int coachLeft,
                  int economyLeft, int price) {
        this.flightID = new SimpleIntegerProperty(flightID);
        this.planeID = new SimpleIntegerProperty(planeID);
        this.airlineID = new SimpleIntegerProperty(airlineID);
        this.scheduleID = new SimpleIntegerProperty(scheduleID);
        this.firstClassLeft = new SimpleIntegerProperty(firstClassLeft);
        this.coachLeft = new SimpleIntegerProperty(coachLeft);
        this.economyLeft = new SimpleIntegerProperty(economyLeft);
        this.price = new SimpleDoubleProperty(price);
    }

    public int getFlightID() {
        return flightID.get();
    }

    public void setFlightID(int flightID) {
        this.flightID.set(flightID);
    }

    public int getPlaneID() {
        return planeID.get();
    }

    public void setPlaneID(int planeID) {
        this.planeID.set(planeID);
    }

    public int getAirlineID() {
        return airlineID.get();
    }

    public void setAirlineID(int airlineID) {
        this.airlineID.set(airlineID);
    }

    public int getScheduleID() {
        return scheduleID.get();
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID.set(scheduleID);
    }

    public int getFirstClassLeft() {
        return firstClassLeft.get();
    }

    public void setFirstClassLeft(int firstClassLeft) {
        this.firstClassLeft.set(firstClassLeft);
    }

    public int getCoachLeft() {
        return coachLeft.get();
    }

    public void setCoachLeft(int coachLeft) {
        this.coachLeft.set(coachLeft);
    }

    public int getEconomyLeft() {
        return economyLeft.get();
    }

    public void setEconomyLeft(int economyLeft) {
        this.economyLeft.set(economyLeft);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    @Override
    public String toString() {
        return getFlightID()+","+getAirlineID()+","+getPlaneID()+","+getScheduleID()+","+
                getCoachLeft()+","+getEconomyLeft()+","+getFirstClassLeft()+","+getPrice()+"\n";
    }

}
