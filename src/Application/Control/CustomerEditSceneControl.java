package Application.Control;

import Application.DataTypes.Customer;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.CustomerEditScene;

public class CustomerEditSceneControl {

    private static Button okButton,cancelButton;
    private static TextField firstNameText, lastNameText, ageText, phoneNumberText, passportNumberText;
    private static Customer customer = new Customer();
    private static boolean okPressed = false;

    public static void initialize(){

        okButton = CustomerEditScene.getOkButton();
        okButton.setOnAction(e-> handle_okB());
        cancelButton = CustomerEditScene.getCancelButton();
        cancelButton.setOnAction(e -> handle_cancelB());

        firstNameText = CustomerEditScene.getFirstNameText();
        lastNameText = CustomerEditScene.getLastNameText();
        ageText = CustomerEditScene.getAgeText();
        phoneNumberText = CustomerEditScene.getPhoneNumberText();
        passportNumberText = CustomerEditScene.getPassportNumberText();
    }

 public static void setCustomer(Customer c){
        customer = c;
        firstNameText.setText(customer.getFirstName());
        lastNameText.setText(customer.getLastName());
        ageText.setText(Integer.toString(customer.getAge()));
        passportNumberText.setText(customer.getPassportNumber());
        phoneNumberText.setText(customer.getPhoneNumber());
    }

    public static void handle_okB(){
        if(isInputValid()){
            customer.setFirstName(firstNameText.getText());
            customer.setLastName(lastNameText.getText());
            customer.setPassportNumber(passportNumberText.getText());
            customer.setPhoneNumber(phoneNumberText.getText());
            customer.setAge(Integer.parseInt(ageText.getText()));

            okPressed = true;
            CustomerEditScene.getDialogStage().close();
        }
    }

    public static void handle_cancelB(){
        okPressed = false;
        CustomerEditScene.getDialogStage().close();

    }


    //metoda za provjeru korisničkog unosa
    public static boolean isInputValid(){
        String error = "";

        if(firstNameText == null || firstNameText.getText().isEmpty())
            error += "Enter a first name!\n";

        if(lastNameText == null || lastNameText.getText().isEmpty())
            error += "Enter a last name!\n";

        if(ageText == null || ageText.getText().isEmpty())
            error += "Enter the age!\n";
        else
            try{
                Integer.parseInt(ageText.getText());
            } catch(NumberFormatException e){
                error += "Enter the age!\n";
            }

        if(phoneNumberText == null || phoneNumberText.getText().isEmpty())
            error +="Enter your cell phone number!\n";

        if(passportNumberText == null || passportNumberText.getText().isEmpty())
            error += "Enter the passport number!\n";

        if(error.equals(""))
            return true;

        else{
            Alert alert = new Alert((Alert.AlertType.WARNING));
            alert.initOwner(MainControl.getWindow());
            alert.setContentText(error);
            alert.setHeaderText("Invalid input!");
            alert.showAndWait();
            return false;
        }
    }

    public static Customer getCustomer(){ return customer; }

    public static boolean isOkPressed(){ return okPressed; }

}
