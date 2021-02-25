package Application.DataTypes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookingTable {

    private IntegerProperty bookingID;
    private StringProperty departureDate, route, customer;


    public BookingTable() {
        bookingID = new SimpleIntegerProperty();
        departureDate = new SimpleStringProperty("");
        route = new SimpleStringProperty("");
        customer = new SimpleStringProperty("");
    }

    public BookingTable(int bookingID, String departDate, String route, String customer) {
        this.bookingID = new SimpleIntegerProperty(bookingID);
        this.departureDate = new SimpleStringProperty(departDate);
        this.route = new SimpleStringProperty(route);
        this.customer = new SimpleStringProperty(customer);
    }

    public int getBookingID() {
        return bookingID.get();
    }

    public void setBookingID(int bookingID) {
        this.bookingID.set(bookingID);
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

    public String getRoute() {
        return route.get();
    }

    public StringProperty routeProperty() {
        return route;
    }

    public void setRoute(String route) {
        this.route.set(route);
    }

    public String getCustomer() {
        return customer.get();
    }

    public StringProperty customerProperty() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer.set(customer);
    }

    @Override
    public String toString() {
        return "BookingTable{" +
                "bookingID=" + bookingID +
                ", departureDate=" + departureDate +
                ", route=" + route +
                ", customer=" + customer +
                '}';
    }

}
