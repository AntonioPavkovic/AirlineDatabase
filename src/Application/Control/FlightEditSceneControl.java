package Application.Control;

import Application.DataTypes.*;
import DataAccess.AirlineData;
import DataAccess.FlightData;
import DataAccess.PlaneData;
import DataAccess.ScheduleData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import sample.FlightsEditScene;

import java.sql.Date;
import java.time.LocalDate;

public class FlightEditSceneControl {

    private static TextField price;
    private static ComboBox<String> route, departureTime, arrivalTime;
    private static ComboBox<Integer> planeID;
    private static DatePicker departureDate;
    private static Button okButton, cancelButton;
    private static Flight flight;
    private static FlightTable flightTable;
    private static boolean okPressed =  false;

    public static void initialize(){

        price = FlightsEditScene.getPrice();
        route = FlightsEditScene.getRoute();
        for(Airline airline: AirlineData.getAirlines()){
            if(!route.getItems().contains(airline.getDepartureCity() + " -> " + airline.getArrivalCity()))
                route.getItems().add(airline.getDepartureCity() + " -> " + airline.getArrivalCity());
        }

        planeID = FlightsEditScene.getPlaneID();
        for(Plane p : PlaneData.getPlanes()){
            if(!planeID.getItems().contains(p.getPlaneID()))
                planeID.getItems().add(p.getPlaneID());
        }

        departureTime= FlightsEditScene.getDepartureTime();
        arrivalTime = FlightsEditScene.getArrivalTime();

        ObservableList<String> times = FXCollections.observableArrayList();
        for(int i=1; i<=24; i++) {
            times.add(i + ":00");
        }

        departureTime.setItems(times);
        arrivalTime.setItems(times);
        departureDate = FlightsEditScene.getDepartureDate();

        okButton = FlightsEditScene.getOkButton();
        okButton.setOnAction( e -> handle_okB());
        cancelButton = FlightsEditScene.getCancelButton();
        cancelButton.setOnAction( e-> handleClose());

    }


    //postavljanje leta za uređivanje
    public static void setFlight(FlightTable f,Flight flig){

        flightTable = f;
        flight = flig;

        for(Flight fl: FlightData.getFlight()) {
            if (fl.getFlightID() == flightTable.getFlightID()) {
                flight = fl;
                break;
            }
        }

        Schedule schedule = new Schedule();
        for(Schedule s : ScheduleData.getSchedules()) {
            if (s.getScheduleID() == flight.getScheduleID()) {
                schedule = s;
                break;
            }
        }

        route.setValue(flightTable.getDepartureCity() + " -> " + f.getArrivalCity());
        planeID.setValue(flight.getPlaneID());
        departureDate.setValue(LocalDate.parse(flightTable.getDepartureDate()));
        departureTime.setValue(schedule.getDepartureTime());
        arrivalTime.setValue(schedule.getArrivalTime());
        price.setText(Double.toString(flightTable.getPrice()));

    }

    public static void handle_okB(){
        if(isInputValid()){

            flight.setPlaneID(planeID.getValue());
            String route1 = route.getValue();

            for(Airline a : AirlineData.getAirlines()){
                if((a.getDepartureCity() + " -> " + a.getArrivalCity()).equalsIgnoreCase(route1)){
                    flight.setAirlineID(a.getAirlineID());
                }
            }

            Schedule schedule = new Schedule();
            schedule.setDepartureTime(departureTime.getValue());
            schedule.setArrivalTime(arrivalTime.getValue());
            schedule.setDepartureDate(Date.valueOf(departureDate.getValue()));
            schedule.setArrivalDate(Date.valueOf(departureDate.getValue()));
            flight.setScheduleID(ScheduleData.insertSchedule(schedule));


            flight.setPrice(Double.parseDouble(price.getText()));

            okPressed = true;
            FlightsEditScene.getDialogStage().close();
        }

    }

    public static void handleClose(){
        okPressed = false;
        FlightsEditScene.getDialogStage().close();
    }


    //metoda za provjeru korisničkog unosa
    public static boolean isInputValid(){
        String error = "";

        if(route.getValue().equalsIgnoreCase(" -> "))
            error += "Invalid route!\n";

        try {

            if(Double.parseDouble(price.getText()) < 10)
                    error+="Invalid base price!\n";
            Double.parseDouble(price.getText());
        }
        catch(NumberFormatException e){
            error += "Invalid base price!\n";
        }

        if(error=="")
            return true;
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(FlightsEditScene.getDialogStage());
            alert.setContentText(error);
            alert.setHeaderText("Invalid input!");
            alert.showAndWait();
            return false;
        }
    }

    public static Flight getFlight() {
        return flight;
    }

    public static boolean isOkPressed(){
        return okPressed;
    }

}
