package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuScene {

    private static Scene scene;
    private static Pane layout;
    private static VBox vbox;
    private static Label quoteLabel, welcomeLabel;
    private static ImageView imageView;
    private static Button bookingsButton, customersButton, flightsButton, planesButton, exitButton;

    public static void initialize() {

        quoteLabel = new Label("There's just nothing like seeing the world from \n\n thousands of feet " +
                "up in the air");
        quoteLabel.setId("quote");
        quoteLabel.relocate(50,520);

        welcomeLabel = new Label("Welcome to APAirlines\n");
        welcomeLabel.setId("welcome");
        welcomeLabel.setAlignment(Pos.CENTER);
        welcomeLabel.relocate(810,90);

        imageView = new ImageView("/sample/img.png");
        imageView.setFitHeight(380);
        imageView.setFitWidth(680);
        imageView.relocate(30,85);

        bookingsButton = new Button("Bookings");
        bookingsButton.setAlignment(Pos.CENTER);
        bookingsButton.setDefaultButton(true);
        bookingsButton.getStyleClass().add("menu-button");

        customersButton = new Button("Customers");
        customersButton.setAlignment(Pos.CENTER);
        customersButton.getStyleClass().add("menu-button");

        flightsButton = new Button("Flights");
        flightsButton.setAlignment(Pos.CENTER);
        flightsButton.getStyleClass().add("menu-button");

        planesButton = new Button("Planes");
        planesButton.setAlignment(Pos.CENTER);
        planesButton.getStyleClass().add("menu-button");


        exitButton = new Button("Exit");
        exitButton.setId("exit-button");
        exitButton.setAlignment(Pos.CENTER);

        vbox = new VBox(10);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.relocate(940,320);
        vbox.getChildren().addAll(bookingsButton,customersButton,flightsButton,planesButton,exitButton);

        layout = new Pane();
        layout.getChildren().addAll(quoteLabel, imageView, welcomeLabel, vbox);

        scene = new Scene(layout, 1200, 700);
        scene.getStylesheets().add("sample/style.css");

        System.out.println("Initialized");

    }

    public static Scene getScene() {
        return scene;
    }

    public static Pane getLayout() {
        return layout;
    }

    public static Button getBookingsButton() {
        return bookingsButton;
    }

    public static Button getCustomersButton() {
        return customersButton;
    }

    public static Button getFlightsButton() {
        return flightsButton;
    }

    public static Button getPlanesButton() {
        return planesButton;
    }

    public static Button getExitButton() {
        return exitButton;
    }
}
