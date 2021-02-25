package sample;

import Application.DataTypes.Plane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ViewPlaneScene {

    private static TableView<Plane> table;
    private static TableColumn<Plane, Integer> planeIDColumn;
    private static TableColumn<Plane, String> planeNameColumn;
    private static TableColumn<Plane, Integer> firstClassColumn;
    private static TableColumn<Plane, Integer> coachColumn;
    private static TableColumn<Plane, Integer> economyColumn;
    private static Button addButton,backButton,editButton,exportPlaneButton;
    private static VBox buttonsVbox;
    private static Pane layout;
    private static Scene scene;

    public static void initialize() {

        table=new TableView();
        table.relocate(28,34);
        table.setMinHeight(609);
        table.setMinWidth(950);

        planeIDColumn=new TableColumn("ID");
        planeIDColumn.setCellValueFactory(cellData -> cellData.getValue().planeIDProperty().asObject());
        planeIDColumn.setMinWidth(60);
        planeIDColumn.setMaxWidth(70);

        planeNameColumn=new TableColumn("Name");
        planeNameColumn.setCellValueFactory(cellData->cellData.getValue().planeNameProperty());

        firstClassColumn = new TableColumn("First Class");
        firstClassColumn.setCellValueFactory(cellData -> cellData.getValue().firstClassProperty().asObject());

        coachColumn = new TableColumn("Coach Class");
        coachColumn.setCellValueFactory(cellData->cellData.getValue().coachProperty().asObject());

        economyColumn = new TableColumn("Economy Class");
        economyColumn.setCellValueFactory(cellData->cellData.getValue().economyProperty().asObject());

        table.getColumns().addAll(planeIDColumn,planeNameColumn,firstClassColumn,coachColumn,economyColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        addButton = new Button("Add");
        backButton = new Button("Back");
        editButton = new Button("Edit");
        exportPlaneButton = new Button("Export");

        buttonsVbox = new VBox();
        buttonsVbox.getChildren().addAll(addButton,editButton,exportPlaneButton,backButton);
        buttonsVbox.setSpacing(20);
        buttonsVbox.relocate(1020,250);

        layout=new Pane();
        layout.getChildren().addAll(table,buttonsVbox);
        scene = new Scene(layout,1200,700);
        scene.getStylesheets().add("/sample/style.css");

        System.out.println("Initialized");

    }

    public static TableView<Plane> getTable() {
        return table;
    }

    public static Button getAddButton() {
        return addButton;
    }

    public static Button getBackButton() {
        return backButton;
    }

    public static Button getEditButton() {
        return editButton;
    }

    public static Button getExportPlaneButton() {
        return exportPlaneButton;
    }

    public static Pane getLayout() {
        return layout;
    }

    public static Scene getScene() {
        return scene;
    }
}
