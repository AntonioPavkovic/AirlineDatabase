package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlaneEditScene {

    private static Stage dialogStage;
    private static Scene scene;
    private static GridPane gridPane;
    private static VBox vboxLabel, vboxText;
    private static Label planeNameLabel, firstClassLabel, coachLabel, economyLabel, titleLabel;
    private static Button okButton, cancelButton;
    private static TextField planeNameText, firstClassText, coachText, economyText;
    private static BorderPane borderPane;
    private static HBox buttons;

    public static void initialize() {

        planeNameLabel = new Label("Plane name");
        firstClassLabel = new Label("First class");
        coachLabel = new Label("Coach class");
        economyLabel = new Label("Economy class");

        planeNameText = new TextField();
        firstClassText = new TextField();
        coachText = new TextField();
        economyText = new TextField();

        titleLabel = new Label();
        titleLabel.setText("\n\nPlane details");
        titleLabel.setStyle("-fx-font-size:20pt");

        okButton = new Button("Ok");
        okButton.setDefaultButton(true);
        cancelButton = new Button("Cancel");

        gridPane = new GridPane();
        borderPane = new BorderPane();
        gridPane.setAlignment(Pos.CENTER);

        vboxLabel = new VBox();
        vboxText = new VBox();
        buttons = new HBox();

        buttons.setSpacing(10);
        buttons.getChildren().addAll(okButton,cancelButton);
        buttons.setAlignment(Pos.CENTER);

        gridPane.setHgap(10);
        vboxText.setSpacing(25);
        vboxLabel.setSpacing(34);

        vboxLabel.getChildren().addAll(planeNameLabel,firstClassLabel,coachLabel,economyLabel);
        vboxText.getChildren().addAll(planeNameText,firstClassText,coachText,economyText, buttons);
        vboxText.setAlignment(Pos.CENTER);
        gridPane.add(vboxLabel, 0, 0);
        gridPane.add(vboxText, 1, 0);
        borderPane.setCenter(gridPane);
        borderPane.setTop(titleLabel);
        borderPane.setAlignment(titleLabel, Pos.CENTER);

        scene = new Scene(borderPane, 500, 600);
        scene.getStylesheets().add("/sample/style.css");


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

    public static Button getOkButton() {
        return okButton;
    }

    public static Button getCancelButton() {
        return cancelButton;
    }

    public static TextField getPlaneNameText() {
        return planeNameText;
    }

    public static TextField getFirstClassText() {
        return firstClassText;
    }

    public static TextField getCoachText() {
        return coachText;
    }

    public static TextField getEconomyText() {
        return economyText;
    }

}
