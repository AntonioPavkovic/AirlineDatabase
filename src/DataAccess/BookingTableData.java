package DataAccess;

import Application.DataTypes.BookingTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class BookingTableData {

    private static Statement statement;
    private static ObservableList<BookingTable> bookingTableItems;

    public static ObservableList<BookingTable> getBookingTableItems() {
        bookingTableItems = FXCollections.observableArrayList();

        try{
            statement = DataConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT departureDate, departureCity, arrivalCity," +
                    " firstName, lastName, bookingID " +
                    "FROM booking b JOIN flight f " +
                    "ON b.flightID = f.flightID " +
                    "JOIN customer c " +
                    "ON b.customerID = c.customerID " +
                    "JOIN schedule s " +
                    "ON f.scheduleID = s.scheduleID " +
                    "JOIN airline a " +
                    "ON f.airlineID = a.airlineID " +
                    "ORDER BY departureDate;");

            if(rs!=null)
                while(rs.next()){
                    BookingTable b = new BookingTable();
                    b.setDepartureDate(rs.getDate(1).toString());
                    b.setRoute(rs.getString(2) + " -> " + rs.getString(3));
                    b.setCustomer(rs.getString(4) + " " + rs.getString(5));
                    b.setBookingID(rs.getInt(6));

                    bookingTableItems.add(b);
                }
        }

        catch(Exception e){
            e.printStackTrace();
        }

        return  bookingTableItems;
    }

}
