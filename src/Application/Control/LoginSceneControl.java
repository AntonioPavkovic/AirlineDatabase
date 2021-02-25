package Application.Control;

import Application.DataTypes.Admin;
import DataAccess.AdminData;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.LoginScene;

import java.util.ArrayList;

public class LoginSceneControl {

    private static Button loginButton;
    private static TextField usernameField;
    private static TextField passwordField;
    private static ArrayList<Admin> admins;
    private static String username;
    private static String password;

    public static void initialize(){

        usernameField = LoginScene.getUsernameField();
        passwordField = LoginScene.getPasswordField();

        admins = AdminData.getAdmins();
        loginButton = LoginScene.getLoginButton();
        loginButton.setDefaultButton(true);
        loginButton.setOnAction(e->{
            username = usernameField.getText();
            password = passwordField.getText();

            handleLoginButton();
        });

    }

    public static void handleLoginButton(){
        int ok = 0;

        if(isInputValid()) {
            //provjeriti korisničke podatke u bazi
            for(Admin admin : AdminData.getAdmins()) {
                if (admin.getAdminID() == Integer.parseInt(username) && admin.getPassword().equalsIgnoreCase(password)) {

                    MainControl.showMenuScene(); //provjera username i password
                    System.out.println("Login successful");
                    ok = 1;
                    break;
                }
            }

            if(ok == 0) {
                usernameField.clear();
                passwordField.clear();

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Login failed");
                alert.setContentText("Invalid username or password");
                alert.initOwner(MainControl.getWindow());
                alert.showAndWait();
            }
        }
    }


    //metoda za provjeru korisničkog unosa
    public static boolean isInputValid(){
        String error = "";

        if(username.isEmpty())
            error+="Invalid username!\n";
        else try{
            Integer.parseInt(username);
        }
        catch(NumberFormatException e){
            error +="Invalid username!\n";
        }

        if(password.isEmpty())
            error += "Invalid password!\n";

        if(error=="")
            return true;

        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(error);
            alert.setHeaderText("Invalid input!");
            alert.initOwner(MainControl.getWindow());
            alert.showAndWait();
            return false;
        }
    }
}
