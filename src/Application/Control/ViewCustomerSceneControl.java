package Application.Control;

import Application.DataTypes.Customer;
import DataAccess.CustomerData;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.ViewCustomersScene;

public class ViewCustomerSceneControl {

    private static TableView<Customer> table;
    private static TextField search;
    private static ObservableList<Customer> customers;
    private static ObservableList<Customer> tableItems;
    private static Button backButton, addButton, editButton;

    public static void initialize(){
        table = ViewCustomersScene.getTable();
        table.setItems(CustomerData.getCustomers());

        backButton = ViewCustomersScene.getBackButton();
        backButton.setOnAction(e -> handle_backB());
        addButton = ViewCustomersScene.getAddButton();
        addButton.setOnAction(e -> handle_addB());
        editButton = ViewCustomersScene.getEditButton();
        editButton.setOnAction(e -> handle_editB());

        search = ViewCustomersScene.getSearch();
        customers = table.getItems();
        initializeSearch();
    }


    //add button
    public static void handle_addB() {
        Customer customer = new Customer();
        boolean okPressed = MainControl.showCustomerEditScene(customer);

        if(okPressed) {
            customer = CustomerEditSceneControl.getCustomer();
            CustomerData.insertCustomer(customer); //dodati kupca u bazu podataka

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(MainControl.getWindow());
            alert.setContentText("Customer added!");
            alert.showAndWait();

            System.out.println("A new customer has been added");
        }
    }

    //edit button
    public static void handle_editB(){
        Customer customer = table.getSelectionModel().getSelectedItem();

        if(customer != null) {
            boolean okPressed = MainControl.showCustomerEditScene(customer);

            if(okPressed) {
                customer = CustomerEditSceneControl.getCustomer();
                CustomerData.updateCustomer(customer); //uredi kupca u bazi podataka

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(MainControl.getWindow());
                alert.setContentText("Customer edited!");
                alert.showAndWait();

                System.out.println("Customer edited");
            }
        }

        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(MainControl.getWindow());
            alert.setHeaderText("Select a customer!");
            alert.setContentText("No customers selected!");
            alert.showAndWait();
        }
    }

    //search bar
    public static void initializeSearch(){
        search.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (search.textProperty().get().isEmpty()) {
                    table.setItems(customers);
                    return;
                }

                tableItems = FXCollections.observableArrayList();

                for(Customer c : customers){
                    if(c.getFirstName().toUpperCase().contains(search.getText().toUpperCase())||
                            c.getLastName().toUpperCase().contains(search.getText().toUpperCase())||
                            c.getPassportNumber().toUpperCase().contains(search.getText().toUpperCase())||
                            c.getPhoneNumber().contains(search.getText())){

                        tableItems.add(c);
                    }
                }

                table.setItems(tableItems);
            }

        });
    }

    public static void handle_backB(){ MainControl.showMenuScene(); }

}
