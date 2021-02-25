package Application.Control;

import Application.DataTypes.Plane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.PlaneEditScene;

public class PlaneEditSceneControl {

    private static Button okButton, cancelButton;
    private static TextField planeNameText, firstClassText, coachText, economyText;
    private static Plane plane;
    private static boolean okPressed = false;

    public static void initialize(){

        okButton = PlaneEditScene.getOkButton();
        okButton.setOnAction(e -> handleOkButton());
        cancelButton = PlaneEditScene.getCancelButton();
        cancelButton.setOnAction(e -> handleClose());

        planeNameText = PlaneEditScene.getPlaneNameText();
        firstClassText = PlaneEditScene.getFirstClassText();
        coachText = PlaneEditScene.getCoachText();
        economyText = PlaneEditScene.getEconomyText();

    }

    public static void setPlane(Plane p) {
        plane = p;

        planeNameText.setText(plane.getPlaneName());
        firstClassText.setText(Integer.toString(plane.getFirstClass()));
        coachText.setText(Integer.toString(plane.getCoach()));
        economyText.setText(Integer.toString(plane.getEconomy()));
    }

    public static void handleOkButton(){
        if(isInputValid()){
            plane.setPlaneName(planeNameText.getText());
            plane.setFirstClass(Integer.parseInt(firstClassText.getText()));
            plane.setCoach(Integer.parseInt(coachText.getText()));
            plane.setEconomy(Integer.parseInt(economyText.getText()));

            okPressed = true;
            PlaneEditScene.getDialogStage().close();
        }
    }

    public static void handleClose(){
        okPressed = false;
        PlaneEditScene.getDialogStage().close();
    }

    public static boolean isInputValid(){
        String error = "";

        if(planeNameText == null || planeNameText.getText().isEmpty())
            error += "Invalid aircraft name!\n";

        if(firstClassText == null || firstClassText.getText().isEmpty())
            error += "Invalid number of seats for first class!\n";
        else
            try{Integer.parseInt(firstClassText.getText());}
            catch(NumberFormatException e){
                error += "Invalid number of seats for first class!\n";
            }

        if(coachText == null || coachText.getText().isEmpty())
            error += "Invalid number of seats for coach class!\n";
        else
            try{Integer.parseInt(coachText.getText());}
            catch(NumberFormatException e){
                error += "Invalid number of seats for coach class!\n";
            }

        if(economyText == null || economyText.getText().isEmpty())
            error += "Invalid number of seats for economy class!\n";
        else
            try{Integer.parseInt(economyText.getText());}
            catch(NumberFormatException e){
                error += "Invalid number of seats for economy class!\n";
            }

        if(error.equals(""))
            return true;

        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(MainControl.getWindow());
            alert.setContentText(error);
            alert.setHeaderText("Wrong entry!");
            alert.showAndWait();
            return false;
        }
    }

    public static Plane getPlane() { return plane; }

    public static boolean isOkPressed() { return okPressed; }
}
