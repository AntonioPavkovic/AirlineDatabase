package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookingEditScene {

    private static Stage dialogStage;
    private static Scene scene;
    private static Pane layout;
    private static Label customerLabel, addCustomerLabel, bookingLabel, routeLabel, departureDateLabel;
    private static Label categoryLabel, priceLabel;
    private static VBox labelsLayout, detailsLayout;
    private static ComboBox<String> customerBox;
    private static ComboBox<String> routeBox;
    private static DatePicker departureDatePicker;
    private static RadioButton firstClass;
    private static RadioButton coachClass;
    private static RadioButton economyClass;
    private static ToggleGroup g;
    private static HBox toggleBox;
    private static Button addCustomer;
    private static Button okButton;
    private static Button cancelButton;
    private static Label priceLabelObs;
    private static HBox buttons;

    public static void initialize() {

        bookingLabel = new Label("Booking details");
        bookingLabel.relocate(190, 51);
        bookingLabel.setStyle("-fx-font-size:20pt");

        customerLabel = new Label("Select customer");

        addCustomerLabel = new Label("OR");

        routeLabel = new Label("Route");

        departureDateLabel = new Label("Departure date");

        categoryLabel = new Label("Category");

        priceLabel = new Label("Price");

        labelsLayout = new VBox();
        labelsLayout.getChildren().addAll(customerLabel,addCustomerLabel,
                routeLabel,departureDateLabel,categoryLabel,priceLabel);
        labelsLayout.setSpacing(34);
        labelsLayout.relocate(41, 138);

        customerBox =new ComboBox<>();
        customerBox.setMinWidth(270);

        addCustomer = new Button("New customer");
        addCustomer.setMaxWidth(270);

        routeBox = new ComboBox<>();
        routeBox.setMaxWidth(270);

        departureDatePicker = new DatePicker();
        departureDatePicker.setMinWidth(270);

        firstClass = new RadioButton("First class");
        coachClass = new RadioButton("Coach");
        economyClass = new RadioButton("Economy");

        g = new ToggleGroup();
        firstClass.setToggleGroup(g);
        coachClass.setToggleGroup(g);
        economyClass.setToggleGroup(g);

        toggleBox = new HBox();
        toggleBox.getChildren().addAll(firstClass, coachClass, economyClass);
        toggleBox.setSpacing(10);

        priceLabelObs = new Label("");
        cancelButton = new Button("Cancel");
        okButton = new Button("OK");

        buttons = new HBox(10);
        buttons.getChildren().addAll(okButton, cancelButton);
        buttons.setAlignment(Pos.CENTER);

        detailsLayout = new VBox();
        detailsLayout.getChildren().addAll(customerBox,addCustomer, routeBox,departureDatePicker,
                                            toggleBox,priceLabelObs,buttons);
        detailsLayout.setSpacing(24);
        detailsLayout.relocate(210,139);
        toggleBox.setPadding(new Insets(10,0,0,0));
        priceLabelObs.setPadding(new Insets(10,0,0,0));

        layout = new Pane();
        layout.getChildren().addAll(bookingLabel,labelsLayout,detailsLayout);

        scene = new Scene(layout, 500, 600);
        scene.getStylesheets().addAll("/sample/style.css");

        dialogStage = new Stage();
        dialogStage.getIcons().add(new Image("/sample/icon.png"));
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

    public static ComboBox<String> getCustomerBox() {
        return customerBox;
    }

    public static ComboBox<String> getRouteBox() {
        return routeBox;
    }

    public static DatePicker getDepartureDatePicker() {
        return departureDatePicker;
    }

    public static RadioButton getFirstClass() {
        return firstClass;
    }

    public static RadioButton getCoachClass() {
        return coachClass;
    }

    public static RadioButton getEconomyClass() {
        return economyClass;
    }

    public static Button getAddCustomer() {
        return addCustomer;
    }

    public static Button getOkButton() {
        return okButton;
    }

    public static Button getCancelButton() {
        return cancelButton;
    }

    public static Label getPriceLabelObs() {
        return priceLabelObs;
    }

}
