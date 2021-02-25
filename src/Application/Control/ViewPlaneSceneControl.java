package Application.Control;

import Application.DataTypes.Plane;
import DataAccess.PlaneData;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import sample.ViewPlaneScene;

public class ViewPlaneSceneControl {

    private static TableView<Plane> table;
    private static Button addButton, backButton, editButton, exportPlaneButton;

    public static void initialize(){
        table = ViewPlaneScene.getTable();
        table.setItems(PlaneData.getPlanes());

        addButton =  ViewPlaneScene.getAddButton();
        addButton.setOnAction(e -> handle_addButton());
        backButton = ViewPlaneScene.getBackButton();
        backButton.setOnAction(e -> handle_backButton());
        editButton = ViewPlaneScene.getEditButton();
        editButton.setOnAction(e -> handle_editButton());
        exportPlaneButton = ViewPlaneScene.getExportPlaneButton();
        exportPlaneButton.setOnAction(event -> handle_exportButton());

    }

    public static void handle_addButton(){
        Plane plane = new Plane();
        boolean okPressed = MainControl.showPlaneEditScene(plane);

        if(okPressed) {
            plane = PlaneEditSceneControl.getPlane();
            PlaneData.insertPlanes(plane); //add plane to database

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(MainControl.getWindow());
            alert.setContentText("Plane added!");
            alert.showAndWait();

            System.out.println("A new plane was added");
        }
    }
    public static void handle_editButton(){
        Plane plane = table.getSelectionModel().getSelectedItem();

        if(plane!=null){
            boolean okPressed = MainControl.showPlaneEditScene(plane);

            if(okPressed){
                plane = PlaneEditSceneControl.getPlane();
                PlaneData.updatePlane(plane); // uređivanje ravnine u bazi podataka

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(MainControl.getWindow());
                alert.setContentText("The plane has been edited!");
                alert.showAndWait();

                System.out.println("The plane has been edited!");
            }
        }

        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(MainControl.getWindow());
            alert.setHeaderText("Select a plane!");
            alert.setContentText("No plane selected!");
            alert.showAndWait();
        }
    }

    public  static  void handle_exportButton() {
        PlaneData.getPlanes();
        PlaneData.exportPlanes();
    }
    public static void handle_backButton(){
        MainControl.showMenuScene();
    }
}
