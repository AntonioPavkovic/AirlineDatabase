package sample;

import Application.DataTypes.FlightTable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ViewFlightsScene {

    private static Pane layout;
    private static Scene scene;
    private static TableView<FlightTable> table;
    private static TableColumn<FlightTable, Integer> flightIDColumn, economyColumn, coachColumn, firstClassColumn;
    private static TableColumn<FlightTable, String> departureDateColumn, departureCityColumn, arrivalCityColumn;
    private static TableColumn<FlightTable, Double> priceColumn;
    private static TextField search;
    private static HBox buttonLayout;
    private static Button backButton, addButton, editButton, exportFlightsButton;

    public static void initialize() {

        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMinSize(1116,591);
        table.relocate(42,32);

        flightIDColumn = new TableColumn<>("ID");
        flightIDColumn.setCellValueFactory(cellData -> cellData.getValue().flightIDProperty().asObject());
        flightIDColumn.setMaxWidth(70);
        flightIDColumn.setMinWidth(60);

        departureDateColumn = new TableColumn<>("Departure date");
        departureDateColumn.setCellValueFactory(cellData -> cellData.getValue().departureDateProperty());
        departureCityColumn = new TableColumn<>("Departure city");
        departureCityColumn.setCellValueFactory(cellData -> cellData.getValue().departureCityProperty());
        arrivalCityColumn = new TableColumn<>("Arrival city");
        arrivalCityColumn.setCellValueFactory(cellData -> cellData.getValue().arrivalCityProperty());

        priceColumn = new TableColumn<>("Base price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        economyColumn = new TableColumn<>("Economy seat");
        economyColumn.setCellValueFactory(cellData -> cellData.getValue().economyLeftProperty().asObject());
        coachColumn = new TableColumn<>("Coach seat");
        coachColumn.setCellValueFactory(cellData -> cellData.getValue().coachLeftProperty().asObject());
        firstClassColumn = new TableColumn<>("First seat");
        firstClassColumn.setCellValueFactory(cellData -> cellData.getValue().firstClassLeftProperty().asObject());


        table.getColumns().addAll(flightIDColumn,departureDateColumn,departureCityColumn,arrivalCityColumn,
                priceColumn,economyColumn,coachColumn,firstClassColumn);

        search = new TextField();
        search.setPromptText("search");
        search.setMinWidth(545);
        search.relocate(42,642);

        backButton = new Button("Back");
        addButton = new Button("Add");
        editButton = new Button("Edit");
        exportFlightsButton = new Button("Export");

        buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.relocate(740,642);
        buttonLayout.getChildren().addAll(addButton,editButton,exportFlightsButton,backButton);

        layout = new Pane();
        layout.getChildren().addAll(table,search,buttonLayout);
        scene = new Scene(layout, 1200, 700);
        scene.getStylesheets().add("/sample/style.css");

        System.out.println("Initialized");
    }

    public static Pane getLayout() {
        return layout;
    }

    public static Scene getScene() {
        return scene;
    }

    public static TableView<FlightTable> getTable() {
        return table;
    }

    public static TextField getSearch() {
        return search;
    }

    public static Button getBackButton() {
        return backButton;
    }

    public static Button getAddButton() {
        return addButton;
    }

    public static Button getEditButton() {
        return editButton;
    }

    public static Button getExportFlightsButton() {
        return exportFlightsButton;
    }
}
