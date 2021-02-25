package Application.Control;

import Application.DataTypes.*;
import DataAccess.DataConnection;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.*;

public class MainControl {

    public static Stage window = new Stage();

    public static void showMenuScene(){
        MenuScene.initialize();
        MenuSceneControl.initialize();

        window.setScene(MenuScene.getScene());
        window.setTitle("APAirlineDatabases");
        window.centerOnScreen();

    }

    public static void showLoginScene() {

        DataConnection.connect();
        LoginScene.initialize();
        LoginSceneControl.initialize();


        window.setScene(LoginScene.getScene());
        window.setTitle("APAirlineDatabases");
        window.getIcons().add(new Image("/sample/icon.png"));
        window.show();
        window.centerOnScreen();
    }

    public static void showViewBookingScene(){

        ViewBookingScene.initialize();
        ViewBookingSceneControl.initialize();

        //set scene
        window.setScene(ViewBookingScene.getScene());
        window.setTitle("APAirlineDatabases - Bookings");
    }


    public static void showViewCustomerScene(){

        ViewCustomersScene.initialize();
        ViewCustomerSceneControl.initialize();

        window.setScene(ViewCustomersScene.getScene());
        window.setTitle("APAirlineDatabases - Customers");
    }



    public static void showViewFlightsScene(){

        ViewFlightsScene.initialize();
        ViewFlightSceneControl.initialize();

        window.setScene(ViewFlightsScene.getScene());
        window.setTitle("APAirlineDatabases - Flights");
    }


    public static void showViewPlanesScene(){
        ViewPlaneScene.initialize();
        ViewPlaneSceneControl.initialize();

        window.setScene(ViewPlaneScene.getScene());
        window.setTitle("APAirlineDatabases - Planes");
    }

    public static boolean showPlaneEditScene(Plane plane){
        PlaneEditScene.initialize();
        PlaneEditSceneControl.initialize();
        PlaneEditSceneControl.setPlane(plane);

        PlaneEditScene.getDialogStage().initOwner(window);
        PlaneEditScene.getDialogStage().setTitle("Plane menu");
        PlaneEditScene.getDialogStage().showAndWait();

        return PlaneEditSceneControl.isOkPressed();
    }

    public static boolean showCustomerEditScene(Customer customer){
        CustomerEditScene.initialize();

        CustomerEditSceneControl.initialize();
        CustomerEditSceneControl.setCustomer(customer);

        CustomerEditScene.getDialogStage().initOwner(window);
        CustomerEditScene.getDialogStage().setTitle("Customer menu");
        CustomerEditScene.getDialogStage().showAndWait();

        return CustomerEditSceneControl.isOkPressed();
    }


    public static boolean showFlightEditScene(FlightTable flightTable, Flight flight){
        FlightsEditScene.initialize();

        FlightEditSceneControl.initialize();
        FlightEditSceneControl.setFlight(flightTable,flight);

        FlightsEditScene.getDialogStage().initOwner(window);
        FlightsEditScene.getDialogStage().setTitle("Flight menu");
        FlightsEditScene.getDialogStage().showAndWait();

        return FlightEditSceneControl.isOkPressed();
    }


    public static boolean showBookingEditScene(BookingTable bookingTable, Booking booking) {
        BookingEditScene.initialize();

        BookingEditSceneControl.initialize();
        BookingEditSceneControl.setBooking(bookingTable, booking);
        BookingEditScene.getDialogStage().initOwner(window);
        BookingEditScene.getDialogStage().setTitle("Booking menu");
        BookingEditScene.getDialogStage(). showAndWait();

        return BookingEditSceneControl.isOkPressed();

    }

    public static Stage getWindow() {
        return window;
    }
}
