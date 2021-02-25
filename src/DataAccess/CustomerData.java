package DataAccess;

import Application.DataTypes.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerData {

    private static Statement statement;
    private static ObservableList<Customer> customers;

    public static ObservableList<Customer> getCustomers(){
        customers = FXCollections.observableArrayList();

        try{
            statement = DataConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM customer");

            if(rs != null)
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerID(rs.getInt(1));
                    customer.setAge(rs.getInt(2));
                    customer.setPassportNumber(rs.getString(3));
                    customer.setFirstName(rs.getString(4));
                    customer.setLastName(rs.getString(5));
                    customer.setPhoneNumber(rs.getString(6));
                    customers.add(customer);
                }
        }

        catch(Exception e){
            e.printStackTrace();
        }

        return customers;
    }

    public static void insertCustomer(Customer customer)
    {
        try{
            statement.executeUpdate("INSERT INTO customer VALUE(default, '" +
                                        customer.getAge() + "', '" +
                                        customer.getPassportNumber() + "', '" +
                                        customer.getFirstName() + "', '" +
                                        customer.getLastName() + "', " +
                                        customer.getPhoneNumber() + ");");
            customers.add(customer);
            customer.setCustomerID(customers.indexOf(customer) + 1);
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updateCustomer(Customer customer){
        try{
            statement.executeUpdate("UPDATE customer SET firstName = '" +
                                        customer.getFirstName() +"', lastName = '" + customer.getLastName() +
                                        "', age = " + customer.getAge() + ", passportNumber = '" +
                                        customer.getPassportNumber() + "', phoneNumber = '" + customer.getPhoneNumber() +
                                        "' WHERE customerID = " + customer.getCustomerID() + ";");
            customers.set(customer.getCustomerID() - 1, customer);
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

}
