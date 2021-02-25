package Application.Control;

import Application.DataTypes.Flight;
import Application.DataTypes.FlightTable;
import Application.DataTypes.Plane;
import DataAccess.FlightData;
import DataAccess.FlightTableData;
import DataAccess.PlaneData;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.ViewFlightsScene;

import java.time.LocalDate;

public class ViewFlightSceneControl {

    private static TableView<FlightTable> table;
    private static ObservableList<FlightTable> flights, tableItems;
    private static TextField search;
    private static Button backButton, addButton, editButton, exportFlightsButton;

    public static void initialize(){

        table = ViewFlightsScene.getTable();
        table.setItems(FlightTableData.getFlightTableItems());

        backButton = ViewFlightsScene.getBackButton();
        backButton.setOnAction(e -> handle_backB());

        addButton = ViewFlightsScene.getAddButton();
        addButton.setOnAction(e -> handle_addB());

        editButton = ViewFlightsScene.getEditButton();
        editButton.setOnAction(e -> handle_editB());

        exportFlightsButton = ViewFlightsScene.getExportFlightsButton();
        exportFlightsButton.setOnAction(event -> handle_exportFlightsB());


        search = ViewFlightsScene.getSearch();
        flights = table.getItems();
        initializeSearch();

    }

    public static void handle_addB() {
        FlightTable flightTable = new FlightTable(LocalDate.now().toString());
        Flight flight = new Flight();

        boolean okPressed = MainControl.showFlightEditScene(flightTable,flight);

        if(okPressed) {
            flight = FlightEditSceneControl.getFlight();

            // dobivanje podataka o sjedištu iz baze podataka aviona
            for(Plane p: PlaneData.getPlanes()){
                if(p.getPlaneID()==flight.getPlaneID()){
                    flight.setFirstClassLeft(p.getFirstClass());
                    flight.setCoachLeft(p.getCoach());
                    flight.setEconomyLeft(p.getEconomy());
                }
            }

            FlightData.insertFlight(flight); // dodavanje leta u baze podataka

            table.setItems(FlightTableData.getFlightTableItems()); // postavljanje stavki tablice
            flights = table.getItems();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(MainControl.getWindow());
            alert.setContentText("Flight added!");
            alert.showAndWait();

            System.out.println("A new flight has been added");
        }
    }

    //edit button
    public static void handle_editB(){
        FlightTable flightTable = table.getSelectionModel().getSelectedItem();
        Flight flight = new Flight();

        if(flightTable != null) {
            boolean okPressed = MainControl.showFlightEditScene(flightTable, flight);

            if (okPressed) {
                flight = FlightEditSceneControl.getFlight();
                FlightData.updateFlight(flight); // ažuriranje leta u bazi podataka

                table.setItems(FlightTableData.getFlightTableItems()); // postavljanje stavki tablice
                flights = table.getItems();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(MainControl.getWindow());
                alert.setContentText("Flight edited!");
                alert.showAndWait();

                System.out.println("Flight edited");
            }
        }

        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(MainControl.getWindow());
            alert.setHeaderText("Select a flight!");
            alert.setContentText("No flight selected!");
            alert.showAndWait();
        }

    }

    //back button
    public static void handle_backB(){ MainControl.showMenuScene(); }

    //export button
    public static void handle_exportFlightsB() {
        FlightData.getFlight();
        FlightData.exportFlights();
    }


    //search bar setup
    public static void initializeSearch(){
        search.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (search.textProperty().get().isEmpty()) {
                    table.setItems(flights);
                    return;
                }

                tableItems = FXCollections.observableArrayList();

                for(FlightTable f : flights){
                    if(f.getDepartureCity().toUpperCase().contains(search.getText().toUpperCase()) ||
                            f.getArrivalCity().toUpperCase().contains(search.getText().toUpperCase())){
                        tableItems.add(f);
                    }
                }

                table.setItems(tableItems);
            }
        });
    }
}
