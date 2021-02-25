package sample;

import Application.DataTypes.Customer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ViewCustomersScene {

    private static Pane layout;
    private static Scene scene;
    private static TableView<Customer> table;
    private static TableColumn<Customer, Integer> idColumn, ageColumn;
    private static TableColumn<Customer, String> firstNameColumn, lastNameColumn, passportColumn, phoneColumn;
    private static TextField search;
    private static HBox buttonLayout;
    private static Button backButton, addButton, editButton;

    public static void initialize() {

        idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(60);
        idColumn.setMaxWidth(70);
        idColumn.setCellValueFactory(cellData -> cellData.getValue().customerIDProperty().asObject());

        firstNameColumn = new TableColumn("First Name");
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        ageColumn = new TableColumn("Age");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());

        phoneColumn = new TableColumn("Phone Number");
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());

        passportColumn = new TableColumn("Passport Number");
        passportColumn.setCellValueFactory(cellData -> cellData.getValue().passportNumberProperty());

        table = new TableView<>();
        table.setMinSize(1116,591);
        table.relocate(42,32);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, ageColumn, phoneColumn, passportColumn);

        search = new TextField();
        search.setPromptText("search");
        search.setMinWidth(545);
        search.relocate(42,642);

        backButton = new Button("Back");
        addButton = new Button("Add");
        editButton = new Button("Edit");

        buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.relocate(862,642);
        buttonLayout.getChildren().addAll(addButton,editButton,backButton);

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

    public static TableView<Customer> getTable() {
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
}
