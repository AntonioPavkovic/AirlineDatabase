package Application.DataTypes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Booking {

    private IntegerProperty bookingID, customerID, flightID;
    private StringProperty fareClass;

    public Booking() {
        bookingID = new SimpleIntegerProperty();
        customerID = new SimpleIntegerProperty();
        flightID = new SimpleIntegerProperty();
        fareClass = new SimpleStringProperty("");
    }

    public Booking(int bookingID, int customerID, int flightID, String fareClass) {
        this.bookingID = new SimpleIntegerProperty(bookingID);
        this.customerID = new SimpleIntegerProperty(customerID);
        this.flightID = new SimpleIntegerProperty(flightID);
        this.fareClass = new SimpleStringProperty(fareClass);
    }



    public int getBookingID() {
        return bookingID.get();
    }

    public void setBookingID(int bookingID) {
        this.bookingID.set(bookingID);
    }

    public int getCustomerID() {
        return customerID.get();
    }

    public void setCustomerID(int customerID) {
        this.customerID.set(customerID);
    }

    public int getFlightID() {
        return flightID.get();
    }

    public void setFlightID(int flightID) {
        this.flightID.set(flightID);
    }

    public String getFareClass() {
        return fareClass.get();
    }

    public void setFareClass(String fare_class) {
        this.fareClass.set(fare_class);
    }


    //toString method
    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", customerID=" + customerID +
                ", flightID=" + flightID +
                ", fareClass=" + fareClass +
                ", reserved" +
                '}';
    }
}
