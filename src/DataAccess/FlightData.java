package DataAccess;

import Application.DataTypes.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.Statement;


public class FlightData {

    private static Statement statement;
    private static ObservableList<Flight> flights;

    public static ObservableList<Flight> getFlight(){
        flights = FXCollections.observableArrayList();

        try{
            statement = DataConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT* FROM flight");

            if(rs != null)
                while (rs.next()) {
                    Flight flight = new Flight();
                    flight.setFlightID(rs.getInt(1));
                    flight.setPlaneID(rs.getInt(2));
                    flight.setAirlineID(rs.getInt(3));
                    flight.setScheduleID(rs.getInt(4));
                    flight.setFirstClassLeft(rs.getInt(5));
                    flight.setCoachLeft(rs.getInt(6));
                    flight.setEconomyLeft(rs.getInt(7));
                    flight.setPrice(rs.getDouble(8));

                    flights.add(flight);
                }
        }

        catch(Exception e){
            e.printStackTrace();
        }

        return flights;
    }



    //metoda za dodavanje leta
    public static void insertFlight(Flight flight) {
        try{
            statement.executeUpdate("INSERT INTO flight VALUE(default, " +
                                        flight.getPlaneID() + ", " +
                                        flight.getAirlineID() + ", " +
                                        flight.getScheduleID() + ", " +
                                        flight.getFirstClassLeft() + ", " +
                                        flight.getCoachLeft() + ", " +
                                        flight.getEconomyLeft() + ", " +
                                        flight.getPrice() +");");
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }


    //metoda za ažuriranje leta
    public static void updateFlight(Flight flight){
        try{
            statement.executeUpdate("UPDATE flight SET planeID = "+
                                        flight.getPlaneID()+", scheduleID = "+
                                        flight.getScheduleID()+", airlineID = "+
                                        flight.getAirlineID()+", firstClassLeft = "+
                                        flight.getFirstClassLeft()+", coachLeft = "+
                                        flight.getCoachLeft()+",economyLeft = "+
                                        flight.getEconomyLeft()+",price = "+
                                        flight.getPrice()+" WHERE flightID = "+
                                        flight.getFlightID()+ ";");
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void exportFlights() {
        String filePath = "C:\\test\\test.txt";
        try{
            FileOutputStream out = new FileOutputStream(filePath);
            for(Flight f : flights)
            {
                String flightData = f.toString();
                out.write(flightData.getBytes());
            }
            out.close();


        }catch (Exception e){
            System.out.println(e);
        }


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Flights exported");
        alert.setHeaderText("Success!");
        alert.showAndWait();
    }

}