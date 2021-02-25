package Application.DataTypes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {

    private IntegerProperty customerID, age;
    private StringProperty firstName, lastName, phoneNumber, passportNumber;

    public Customer() {
        customerID = new SimpleIntegerProperty();
        age = new SimpleIntegerProperty(0);
        firstName = new SimpleStringProperty("");
        lastName = new SimpleStringProperty("");
        phoneNumber = new SimpleStringProperty("");
        passportNumber = new SimpleStringProperty("");
    }

    public Customer(int customerID, int age, String passportNumber, String firstName, String lastName, String phoneNumber) {
        this.customerID = new SimpleIntegerProperty(customerID);
        this.age = new SimpleIntegerProperty(age);
        this.passportNumber = new SimpleStringProperty(passportNumber);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }


    public int getCustomerID() {
        return customerID.get();
    }

    public IntegerProperty customerIDProperty() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID.set(customerID);
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getPassportNumber() {
        return passportNumber.get();
    }

    public StringProperty passportNumberProperty() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber.set(passportNumber);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", age=" + age +
                ", passportNumber=" + passportNumber +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

}
