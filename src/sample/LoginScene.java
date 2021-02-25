package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginScene {

    private static Scene scene;
    private static VBox loginLayout;
    private static Label loginLabel;
    private static Label APAirlinesLabel;
    private static TextField usernameField;
    private static PasswordField passwordField;
    private static Button loginButton;

    public static void initialize() {

        loginLabel = new Label("Login");
        loginLabel.setPadding(new Insets(0, 0, 30, 0));
        loginLabel.setStyle("-fx-font-size: 24pt");

        APAirlinesLabel = new Label("AP Airlines");
        APAirlinesLabel.setId("welcome");
        usernameField=new TextField();
        usernameField.setPromptText("User ID");
        usernameField.setMaxWidth(220);
        usernameField.setAlignment(Pos.CENTER);

        passwordField = new PasswordField();
        passwordField.setAlignment(Pos.CENTER);
        passwordField.setMaxWidth(220);
        passwordField.setPromptText("Password");

        loginButton = new Button("Login");
        loginButton.setMaxWidth(120);

        loginLayout = new VBox(10);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.getChildren().addAll(APAirlinesLabel,loginLabel,usernameField,passwordField,loginButton);

        scene = new Scene(loginLayout,400,500);
        scene.getStylesheets().add("sample/style.css");

        System.out.println("Initialized");

    }

    public static Scene getScene() {
        return scene;
    }

    public static TextField getUsernameField() {
        return usernameField;
    }

    public static PasswordField getPasswordField() {
        return passwordField;
    }

    public static Button getLoginButton() {
        return loginButton;
    }
}
