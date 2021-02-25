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

public class CustomerEditScene {

    private static Stage dialogStage;
    private static Scene scene;
    private static GridPane gridPane;
    private static VBox vBoxLabel, vBoxText;
    private static Label firstName, lastName, age, phoneNumber, passportNumber, title;
    private static Button okButton, cancelButton;
    private static TextField firstNameText, lastNameText, ageText, phoneNumberText, passportNumberText;
    private static BorderPane borderPane;
    private static HBox buttons;

    public static void initialize() {

        title = new Label();
        firstName = new Label();
        lastName = new Label();
        age = new Label();
        phoneNumber = new Label();
        passportNumber = new Label();

        firstNameText = new TextField();
        lastNameText = new TextField();
        ageText = new TextField();
        phoneNumberText = new TextField();
        passportNumberText = new TextField();

        title.setText("\nCustomer Info");
        title.setStyle("-fx-font-size:20pt");

        firstName.setText("First name");
        lastName.setText("Last name");
        age.setText("Age");
        phoneNumber.setText("Phone number");
        passportNumber.setText("Passport number");

        okButton = new Button("Ok");
        okButton.setDefaultButton(true);
        cancelButton = new Button("Cancel");

        buttons = new HBox(10);
        buttons.getChildren().addAll(okButton,cancelButton);
        buttons.setAlignment(Pos.CENTER);
        gridPane = new GridPane();
        borderPane = new BorderPane();
        gridPane.setAlignment(Pos.CENTER);
        vBoxLabel = new VBox();
        vBoxText = new VBox();
        gridPane.setHgap(10);
        vBoxText.setSpacing(25);
        vBoxLabel.setSpacing(34);
        vBoxLabel.getChildren().addAll(firstName,lastName,age,phoneNumber,passportNumber);
        vBoxText.getChildren().addAll(firstNameText,lastNameText,ageText,phoneNumberText, passportNumberText,buttons);
        gridPane.add(vBoxLabel, 0, 0);
        gridPane.add(vBoxText, 1, 0);
        borderPane.setCenter(gridPane);
        borderPane.setTop(title);
        borderPane.setAlignment(title, Pos.CENTER);

        borderPane.getStylesheets().add("/sample/style.css");
        scene = new Scene(borderPane, 500, 600);
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

    public static Button getOkButton() {
        return okButton;
    }

    public static Button getCancelButton() {
        return cancelButton;
    }

    public static TextField getFirstNameText() {
        return firstNameText;
    }

    public static TextField getLastNameText() {
        return lastNameText;
    }

    public static TextField getAgeText() {
        return ageText;
    }

    public static TextField getPhoneNumberText() {
        return phoneNumberText;
    }

    public static TextField getPassportNumberText() {
        return passportNumberText;
    }

}
