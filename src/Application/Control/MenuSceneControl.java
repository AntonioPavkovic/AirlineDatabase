package Application.Control;

import javafx.scene.control.Button;
import sample.MenuScene;

public class MenuSceneControl {

    private static Button bookingsButton, customersButton, flightsButton, planesButton, exitButton;

    public static void initialize() {

        bookingsButton = MenuScene.getBookingsButton();
        bookingsButton.setOnAction(e -> handleBookingsButton());

        customersButton = MenuScene.getCustomersButton();
        customersButton.setOnAction(e -> handleCustomersButton());

        flightsButton = MenuScene.getFlightsButton();
        flightsButton.setOnAction(e -> handleFlightsButton());

        planesButton = MenuScene.getPlanesButton();
        planesButton.setOnAction(e -> handlePlanesButton());

        exitButton = MenuScene.getExitButton();
        exitButton.setOnAction(e -> handleExitsButton());
    }

    public static void handleBookingsButton(){
        MainControl.showViewBookingScene();
    }

    public static void handleCustomersButton(){
        MainControl.showViewCustomerScene();
    }

    public static void handleFlightsButton(){
        MainControl.showViewFlightsScene();
    }

    public static void handlePlanesButton(){
        MainControl.showViewPlanesScene();
    }

    public static void handleExitsButton(){
        MainControl.getWindow().close();
    }
}
