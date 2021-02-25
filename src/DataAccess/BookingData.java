package DataAccess;

import Application.DataTypes.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class BookingData {

    private static Statement statement;
    private static ObservableList<Booking> bookings;

    public static ObservableList<Booking> getBookings() {
        bookings = FXCollections.observableArrayList();

        try{

            statement = DataConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM booking");

            if(rs != null){
                while(rs.next()){
                    Booking booking = new Booking();
                    booking.setBookingID(rs.getInt(1));
                    booking.setCustomerID(rs.getInt(2));
                    booking.setFlightID(rs.getInt(3));
                    booking.setFareClass(rs.getString(4));

                    bookings.add(booking);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return bookings;
    }

    public static void insertBooking(Booking booking)
    {
        try{
            statement.executeUpdate("INSERT INTO booking VALUE(default, " +
                                    booking.getCustomerID() + ", " +
                                    booking.getFlightID() + ", '" +
                                    booking.getFareClass() + "');");
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updateBooking(Booking booking){

        try{

            statement.executeUpdate("UPDATE booking SET customerID = " + booking.getCustomerID() +
                                        ", flightID = " + booking.getFlightID() + ", fareClass = '" +
                                        booking.getFareClass() + "' WHERE bookingID = " +
                                        booking.getBookingID() + ";");

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deleteBooking(Booking booking){
        try{
            statement.executeUpdate("DELETE FROM booking WHERE bookingID = " +
                                    booking.getBookingID() + ";");
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

}
