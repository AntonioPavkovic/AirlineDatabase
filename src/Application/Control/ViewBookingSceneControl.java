package Application.Control;

import Application.DataTypes.*;
import DataAccess.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import sample.ViewBookingScene;

import java.time.LocalDate;
import java.util.Optional;

public class ViewBookingSceneControl {

    private static TableView<BookingTable> table;
    private static Label departureDateObs, arrivalDateObs, categoryObs, firstNameObs, lastNameObs;
    private static Label priceObs, ageObs, passportObs, phoneNumberObs;
    private static TextField search;
    private static Button addBookingButton, cancelButton, backButton, editButton;
    private static ObservableList<BookingTable> bookings, tableItems;
    private static double refund;

    public static void initialize() {

        table = ViewBookingScene.getTable();
        table.setItems(BookingTableData.getBookingTableItems());
        table.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> displayBookingInfo(newValue));

        //flight info labels
        departureDateObs = ViewBookingScene.getDepartureDateObs();
        arrivalDateObs = ViewBookingScene.getArrivalDateObs();
        categoryObs = ViewBookingScene.getCategoryObs();
        priceObs = ViewBookingScene.getPriceObs();

        //customer info labels
        firstNameObs = ViewBookingScene.getFirstNameObs();
        lastNameObs = ViewBookingScene.getLastNameObs();
        ageObs = ViewBookingScene.getAgeObs();
        passportObs = ViewBookingScene.getPassportObs();
        phoneNumberObs = ViewBookingScene.getPhoneNumberObs();

        addBookingButton = ViewBookingScene.getAddBookingButton();
        addBookingButton.setOnAction(e -> handle_addButton());

        cancelButton = ViewBookingScene.getCancelButton();
        cancelButton.setOnAction(e -> handle_cancelButton());

        backButton = ViewBookingScene.getBackButton();
        backButton.setOnAction(e -> handle_backButton());

        editButton = ViewBookingScene.getEditButton();
        editButton.setOnAction(e -> handle_editButton());

        search = ViewBookingScene.getSearchField();
        bookings = table.getItems();
        initializeSearch();
    }


    public static void handle_backButton() {
        MainControl.showMenuScene();
    }

    public static void handle_addButton(){
        BookingTable bookingTable = new BookingTable();
        Booking booking = new Booking();

        boolean okPressed = MainControl.showBookingEditScene(bookingTable, booking);

        if (okPressed) {
            booking = BookingEditSceneControl.getBooking();
            BookingData.insertBooking(booking); // dodavanje rezervacije u bazu podataka

            table.setItems(BookingTableData.getBookingTableItems()); // postavljanje stavki tablice
            bookings = table.getItems();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(MainControl.getWindow());
            alert.setContentText("Reservation added!");
            alert.showAndWait();

            System.out.println("A new reservation has been added");
        }

    }

    public static void handle_editButton(){
        BookingTable bookingTable = table.getSelectionModel().getSelectedItem();
        Booking booking = new Booking();

        if(bookingTable != null) {
            //pretpostavka da će kupac promijeniti kategoriju karte
            for(Flight f: FlightData.getFlight()){
                if(booking.getFlightID()==f.getFlightID())
                    if(booking.getFareClass().equalsIgnoreCase("first class"))
                        f.setFirstClassLeft(f.getFirstClassLeft() + 1);
                    else if(booking.getFareClass().equalsIgnoreCase("coach"))
                        f.setCoachLeft(f.getCoachLeft() + 1);
                    else f.setEconomyLeft(f.getEconomyLeft() + 1);
                FlightData.updateFlight(f);
                break;
            }

            boolean okPressed = MainControl.showBookingEditScene(bookingTable, booking);

            if (okPressed) {
                booking = BookingEditSceneControl.getBooking();
                BookingData.updateBooking(booking); // uređivanje rezervacije u bazi podataka

                table.setItems(BookingTableData.getBookingTableItems());
                table.getSelectionModel().select(bookingTable);
                bookings = table.getItems();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(MainControl.getWindow());
                alert.setContentText("Reservation edited!");
                alert.showAndWait();

                System.out.println("Reservation edited");
            }
        }

        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(MainControl.getWindow());
            alert.setHeaderText("Select a reservation!");
            alert.setContentText("No reservation selected!");
            alert.showAndWait();
        }
    }

    public static void handle_cancelButton() {
        BookingTable bookingTable = table.getSelectionModel().getSelectedItem();
        Booking booking = new Booking();

        if (bookingTable != null) {
            refund = 0.0;
            for (Booking b : BookingData.getBookings())
                if (bookingTable.getBookingID() == b.getBookingID()) {
                    booking = b;
                    break;
                }

            // nijedna rezervacija se ne može otkazati 2 dana prije leta
            if (LocalDate.parse(bookingTable.getDepartureDate()).isBefore(LocalDate.now().plusDays(2))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(MainControl.getWindow());
                alert.setHeaderText("Cancellation is not possible");
                alert.setContentText("It is impossible to cancel a reservation 2 days before the flight");
                alert.showAndWait();
            }

            // postavljanje iznosa povrata
            else {
                for (Flight f : FlightData.getFlight())
                    if (f.getFlightID() == booking.getFlightID()) {
                        if (booking.getFareClass().equalsIgnoreCase("First class"))
                            refund = f.getPrice() + f.getPrice() * 1/2;
                        else if (booking.getFareClass().equalsIgnoreCase("Coach"))
                            refund = (f.getPrice() + f.getPrice() * 1/4) * 85/100;
                        else if (LocalDate.parse(bookingTable.getDepartureDate()).isBefore(LocalDate.now().plusWeeks(2)))
                            refund = 0;
                        else
                            refund = f.getPrice() * 70/100;

                        break;
                    }

                cancelBooking(bookingTable,booking);
            }
        }

        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(MainControl.getWindow());
            alert.setHeaderText("Select a reservation!");
            alert.setContentText("No reservation selected");
            alert.showAndWait();
        }
    }


    //metoda za otkazivanje rezervacije
    public static void cancelBooking(BookingTable bookingTable, Booking booking) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(MainControl.getWindow());
        alert.setHeaderText("Remove the reservation");
        alert.setContentText("Are you sure you want to cancel " + bookingTable.getCustomer() + "reservation?" +
                "\nThe refund will be " + refund + " $.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            BookingData.deleteBooking(booking);

            table.setItems(BookingTableData.getBookingTableItems());
            bookings = table.getItems();

            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.initOwner(MainControl.getWindow());
            alert1.setContentText("Reservation canceled!\n"
                    + bookingTable.getCustomer() + " has been returned " + refund + " $.");
            alert1.showAndWait();

            for (Flight f : FlightData.getFlight())
                if (f.getFlightID() == booking.getFlightID()) {
                    if (booking.getFareClass().equalsIgnoreCase("First class"))
                        f.setFirstClassLeft(f.getFirstClassLeft() + 1);
                    else if (booking.getFareClass().equalsIgnoreCase("Coach"))
                        f.setCoachLeft(f.getCoachLeft() + 1);
                    else
                        f.setEconomyLeft(f.getEconomyLeft() + 1);
                }

                System.out.println("Reservation removed");
        }
        else {
            alert.close();
        }
    }


    // metoda za prikaz detalja rezervacije
    public static void displayBookingInfo(BookingTable buk) {
        if(buk != null) {

            Booking booking = new Booking();
            for(Booking b : BookingData.getBookings())
                if(b.getBookingID() == buk.getBookingID()) {
                    booking = b;
                    break;
                }

            FlightTable flight = new FlightTable();
            for(FlightTable f : FlightTableData.getFlightTableItems())
                if(f.getFlightID() == booking.getFlightID()) {
                    flight = f;
                    break;
                }

            Customer customer = new Customer();
            for(Customer c : CustomerData.getCustomers())
                if(c.getCustomerID() == booking.getCustomerID()) {
                    customer = c;
                    break;
                }


            departureDateObs.setText(flight.getDepartureDate() + ", " + flight.getDepartureCity());
            arrivalDateObs.setText(flight.getDepartureDate() + ", " + flight.getArrivalCity());
            categoryObs.setText(booking.getFareClass());

            if(booking.getFareClass().equalsIgnoreCase("first class"))
                priceObs.setText(String.valueOf(flight.getPrice()+flight.getPrice()*1/2) + " $.");
            else if(booking.getFareClass().equalsIgnoreCase("coach"))
                priceObs.setText(String.valueOf((flight.getPrice()+flight.getPrice()*1/4) + " $."));
            else
                priceObs.setText(String.valueOf(flight.getPrice() + " $."));


            firstNameObs.setText(customer.getFirstName());
            lastNameObs.setText(customer.getLastName());
            ageObs.setText(String.valueOf(customer.getAge()));
            passportObs.setText(customer.getPassportNumber());
            phoneNumberObs.setText(customer.getPhoneNumber());
        }

        else { //no selection
            departureDateObs.setText("");
            arrivalDateObs.setText("");
            categoryObs.setText("");
            priceObs.setText("");
            firstNameObs.setText("");
            lastNameObs.setText("");
            ageObs.setText("");
            passportObs.setText("");
            phoneNumberObs.setText("");
        }
    }

    //Search bar
    public static void initializeSearch(){
        search.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (search.textProperty().get().isEmpty()) {
                    table.setItems(bookings);
                    return;
                }

                tableItems = FXCollections.observableArrayList();

                for(BookingTable b : bookings){
                    if(b.getRoute().toUpperCase().contains(search.getText().toUpperCase())||
                            b.getCustomer().toUpperCase().contains(search.getText().toUpperCase())) {

                        tableItems.add(b);
                    }
                }

                table.setItems(tableItems);
            }
        });
    }
}