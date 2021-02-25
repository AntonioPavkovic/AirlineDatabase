package DataAccess;
import Application.DataTypes.FlightTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;


public class FlightTableData {

    private static Statement statement;
    private static ObservableList<FlightTable> flightTableItems;


    public static ObservableList<FlightTable> getFlightTableItems(){
        flightTableItems = FXCollections.observableArrayList();

        try{
            statement = DataConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT departureDate, departureCity, " +
                    "arrivalCity, flightID, price, firstClassLeft, coachLeft, economyLeft " +
                    "FROM flight f JOIN  schedule s " +
                    "ON f.scheduleID = s.scheduleID " +
                    "JOIN airline a " +
                    "ON f.airlineID = a.airlineID " +
                    "ORDER BY departureDate;");

            if(rs!=null)
                while(rs.next()){
                    FlightTable f = new FlightTable();
                    f.setDepartureDate(rs.getDate(1).toString());
                    f.setDepartureCity(rs.getString(2));
                    f.setArrivalCity(rs.getString(3));
                    f.setFlightID(rs.getInt(4));
                    f.setPrice(rs.getDouble(5));
                    f.setFirstClassLeft(rs.getInt(6));
                    f.setCoachLeft(rs.getInt(7));
                    f.setEconomyLeft(rs.getInt(8));

                    flightTableItems.add(f);
                }
        }

        catch(Exception e){
            e.printStackTrace();
        }


        return  flightTableItems;
    }

}
