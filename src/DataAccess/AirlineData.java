package DataAccess;

import Application.DataTypes.Airline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class AirlineData {

    private static Statement statement;
    private static ObservableList<Airline> airlines;

    public static ObservableList<Airline> getAirlines(){
        airlines = FXCollections.observableArrayList();

        try{

            statement = DataConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM airline");

            if(rs != null){
                while(rs.next()){
                    Airline airline = new Airline();
                    airline.setAirlineID(rs.getInt(1));
                    airline.setDepartureCity(rs.getString(2));
                    airline.setArrivalCity(rs.getString(3));

                    airlines.add(airline);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return airlines;
    }

}
