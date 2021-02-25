package sample;

import Application.DataTypes.BookingTable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ViewBookingScene {

    private static Scene scene;
    private static Pane layout;
    private static TableView<BookingTable> table;
    private static TableColumn<BookingTable, String> dateColumn;
    private static TableColumn<BookingTable, String> nameColumn;
    private static TableColumn<BookingTable, String> routeColumn;
    private static Label flightLabel,departureDateLabel, departureDateObs, arrivalDateLabel;
    private static Label arrivalDateObs, categoryLabel, categoryObs, priceLabel, priceObs;
    private static Label customerDetailsLabel, firstNameLabel, firstNameObs, lastNameLabel;
    private static Label lastNameObs, ageLabel, ageObs, passportLabel, passportObs;
    private static Label phoneNumberLabel, phoneNumberObs;
    private static VBox flightDetails;
    private static VBox flightDetailsObs;
    private static VBox customerDetailsVertical;
    private static VBox customerDetailsVerticalObs;
    private static TextField searchField;
    private static Button addBookingButton;
    private static Button cancelButton;
    private static Button backButton;
    private static Button editButton;
    private static HBox buttonLayout;

    public static void initialize() {

        dateColumn=new TableColumn<>("Departure date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().departureDateProperty());

        nameColumn=new TableColumn<>("Customer name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().customerProperty());

        routeColumn=new TableColumn<>("\t\t\tRoute");
        routeColumn.setMinWidth(160);
        routeColumn.setCellValueFactory(cellData -> cellData.getValue().routeProperty());

        table=new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(dateColumn, routeColumn, nameColumn);
        table.relocate(32,34);
        table.setMinWidth(610);
        table.setMinHeight(580);

        flightLabel=new Label("Flight Details");
        flightLabel.getStyleClass().add("label-simple");
        flightLabel.setStyle("-fx-font-size: 20pt");
        flightLabel.relocate(700, 40);

        departureDateLabel = new Label("Departure");
        departureDateLabel.getStyleClass().add("label-simple");
        departureDateObs = new Label("");

        arrivalDateLabel = new Label("Arrival");
        arrivalDateLabel.getStyleClass().add("label-simple");
        arrivalDateObs = new Label("");

        categoryLabel = new Label("Category");
        categoryLabel.getStyleClass().add("label-simple");

        categoryObs = new Label("");

        priceLabel = new Label("Price");
        priceLabel.getStyleClass().add("label-simple");
        priceObs = new Label("");

        flightDetails = new VBox();
        flightDetails.getChildren().addAll(departureDateLabel,arrivalDateLabel,categoryLabel,priceLabel);
        flightDetails.relocate(700,100);
        flightDetails.setSpacing(25);

        flightDetailsObs = new VBox();
        flightDetailsObs.getChildren().addAll(departureDateObs,arrivalDateObs,categoryObs,priceObs);
        flightDetailsObs.relocate(900,100);
        flightDetailsObs.setSpacing(25);

        customerDetailsLabel=new Label("Customer Details");
        customerDetailsLabel.getStyleClass().add("label-simple");
        customerDetailsLabel.setStyle("-fx-font-size:20pt");
        customerDetailsLabel.relocate(700,310);

        firstNameLabel = new Label("First Name");
        firstNameLabel.getStyleClass().add("label-simple");
        firstNameObs = new Label("");

        lastNameLabel = new Label("Last name");
        lastNameLabel.getStyleClass().add("label-simple");
        lastNameObs=new Label("");

        ageLabel=new Label("Age");
        ageLabel.getStyleClass().add("label-simple");
        ageObs = new Label("");

        passportLabel=new Label("Passport");
        passportLabel.getStyleClass().add("label-simple");
        passportObs=new Label("");

        phoneNumberLabel=new Label("Phone number");
        phoneNumberLabel.getStyleClass().add("label-simple");
        phoneNumberObs=new Label("");

        customerDetailsVertical = new VBox();
        customerDetailsVertical.getChildren().addAll(firstNameLabel,lastNameLabel,
                                                    ageLabel,passportLabel,phoneNumberLabel);
        customerDetailsVertical.relocate(700,370);
        customerDetailsVertical.setSpacing(25);

        customerDetailsVerticalObs=new VBox();
        customerDetailsVerticalObs.getChildren().addAll(firstNameObs,lastNameObs,
                                                        ageObs,passportObs,phoneNumberObs);
        customerDetailsVerticalObs.relocate(900,370);
        customerDetailsVerticalObs.setSpacing(25);

        searchField=new TextField();
        searchField.setPromptText("search");
        searchField.relocate(32,642);
        searchField.setMinWidth(605);

        backButton=new Button("Back");
        addBookingButton=new Button("Add");
        editButton=new Button("Edit");
        cancelButton=new Button("Remove");

        buttonLayout=new HBox();
        buttonLayout.getChildren().addAll(addBookingButton,editButton,cancelButton,backButton);
        buttonLayout.relocate(720,642);
        buttonLayout.setSpacing(20);
        layout=new Pane();
        layout.getChildren().addAll(table,flightDetails,flightDetailsObs,customerDetailsLabel,
                customerDetailsVertical,customerDetailsVerticalObs,flightLabel,buttonLayout,searchField);

        scene = new Scene(layout,1200,700);
        scene.getStylesheets().add("/sample/style.css");


        System.out.println("Initialized");

    }

    public static Scene getScene() {
        return scene;
    }

    public static Pane getLayout() {
        return layout;
    }

    public static TableView<BookingTable> getTable() {
        return table;
    }

    public static Label getDepartureDateObs() {
        return departureDateObs;
    }

    public static Label getArrivalDateObs() {
        return arrivalDateObs;
    }

    public static Label getCategoryObs() {
        return categoryObs;
    }

    public static Label getPriceObs() {
        return priceObs;
    }

    public static Label getFirstNameObs() {
        return firstNameObs;
    }

    public static Label getLastNameObs() {
        return lastNameObs;
    }

    public static Label getAgeObs() {
        return ageObs;
    }

    public static Label getPassportObs() {
        return passportObs;
    }

    public static Label getPhoneNumberObs() {
        return phoneNumberObs;
    }

    public static TextField getSearchField() {
        return searchField;
    }

    public static Button getAddBookingButton() {
        return addBookingButton;
    }

    public static Button getCancelButton() {
        return cancelButton;
    }

    public static Button getBackButton() {
        return backButton;
    }

    public static Button getEditButton() {
        return editButton;
    }

}
