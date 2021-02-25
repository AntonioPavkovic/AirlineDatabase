package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlightsEditScene {

    private static Stage dialogStage;
    private static Scene scene;
    private static Pane layout;
    private static Label flightLabel,timeLabel,routeLabel,dateLabel,planeLabel,toLabel,priceLabel;
    private static ComboBox<String> route,departureTime, arrivalTime;
    private static ComboBox<Integer> planeID;
    private static DatePicker departureDate;
    private static TextField price;
    private static Button okButton, cancelButton;
    private static VBox vBox1, vBox2;
    private static HBox timeBox,buttons;

    public static void initialize() {

        flightLabel = new Label("Flight details");
        flightLabel.relocate(190, 100);
        flightLabel.setAlignment(Pos.CENTER);
        flightLabel.setStyle("-fx-font-size:20pt");

        priceLabel = new Label("Base price");
        routeLabel = new Label("Route");
        dateLabel = new Label("Date");
        timeLabel = new Label("Time");
        planeLabel = new Label ("Plane ID");
        toLabel = new Label("To");

        vBox1 = new VBox();
        vBox1.getChildren().addAll(routeLabel, dateLabel,timeLabel,planeLabel, priceLabel);
        vBox1.relocate(41,202);
        vBox1.setSpacing(34);

        route = new ComboBox<>();
        route.setMinWidth(248);
        departureTime = new ComboBox<>();
        arrivalTime = new ComboBox<>();
        planeID = new ComboBox<>();

        timeBox = new HBox(20);
        timeBox.getChildren().addAll(departureTime,toLabel,arrivalTime);
        price = new TextField();

        departureDate = new DatePicker();
        departureDate.setMinWidth(248);

        //buttons
        okButton = new Button("Ok");
        okButton.setDefaultButton(true);

        cancelButton = new Button("Cancel");

        buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(okButton,cancelButton);

        //selectors layout
        vBox2 = new VBox();
        vBox2.getChildren().addAll(route,departureDate,timeBox,planeID, price, buttons);
        vBox2.setSpacing(24);
        vBox2.relocate(160,200);

        arrivalTime = new ComboBox<>();
        arrivalTime.relocate(350,302);

        layout = new Pane();
        layout.getChildren().addAll(flightLabel, vBox1, vBox2);
        layout.getStylesheets().add("/sample/style.css");

        scene = new Scene(layout,500,600);
        scene.getStylesheets().addAll("/sample/style.css");

        dialogStage = new Stage();
        dialogStage.getIcons().add(new javafx.scene.image.Image("/sample/icon.png"));
        dialogStage.setScene(scene);

        System.out.println("Initialized");

    }

    public static Stage getDialogStage() {
        return dialogStage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static Pane getLayout() {
        return layout;
    }

    public static ComboBox<String> getRoute() {
        return route;
    }

    public static ComboBox<String> getDepartureTime() {
        return departureTime;
    }

    public static ComboBox<String> getArrivalTime() {
        return arrivalTime;
    }

    public static ComboBox<Integer> getPlaneID() {
        return planeID;
    }

    public static DatePicker getDepartureDate() {
        return departureDate;
    }

    public static TextField getPrice() {
        return price;
    }

    public static Button getOkButton() {
        return okButton;
    }

    public static Button getCancelButton() {
        return cancelButton;
    }

}
