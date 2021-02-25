package Application.Control;

import Application.DataTypes.*;
import DataAccess.*;
import javafx.scene.control.*;
import javafx.util.Callback;
import sample.BookingEditScene;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookingEditSceneControl {

    private static ComboBox<String> customerBox;
    private static ComboBox<String> routeBox;
    private static DatePicker departureDatePicker;
    private static RadioButton firstClass;
    private static RadioButton coachClass;
    private static RadioButton economyClass;
    private static Button addCustomer;
    private static Button okButton;
    private static Button cancelButton;
    private static Label priceLabelObs;
    private static boolean okPressed = false;
    private static Booking booking;
    private static BookingTable bookingTable;

    public static void initialize() {

        customerBox = BookingEditScene.getCustomerBox();
        for(Customer c : CustomerData.getCustomers())
            if(!customerBox.getItems().contains(c.getFirstName() + " " + c.getLastName()))
                customerBox.getItems().add(c.getFirstName() + " " + c.getLastName());
        routeBox = BookingEditScene.getRouteBox();
        routeBox.setOnAction(e -> setDatePicker());
        //getting routes
        for(Airline a : AirlineData.getAirlines())
            if(!routeBox.getItems().contains(a.getDepartureCity() + " -> " + a.getArrivalCity()))
                routeBox.getItems().add(a.getDepartureCity() + " -> " + a.getArrivalCity());

        departureDatePicker = BookingEditScene.getDepartureDatePicker();
        departureDatePicker.setOnAction(e-> setAvailableSeat());

        firstClass = BookingEditScene.getFirstClass();
        firstClass.setOnAction(e -> setPrice());
        coachClass = BookingEditScene.getCoachClass();
        coachClass.setOnAction(e -> setPrice());
        economyClass = BookingEditScene.getEconomyClass();
        economyClass.setOnAction(e -> setPrice());

        addCustomer = BookingEditScene.getAddCustomer();
        addCustomer.setOnAction(e -> {
            ViewCustomerSceneControl.handle_addB();
            Customer customer = CustomerEditSceneControl.getCustomer();
            customerBox.getItems().add(customer.getFirstName() + " " + customer.getLastName());
            customerBox.setValue(customer.getFirstName() + " " + customer.getLastName());
        });

        priceLabelObs = BookingEditScene.getPriceLabelObs();
        cancelButton = BookingEditScene.getCancelButton();
        cancelButton.setOnAction(e -> handleClose());
        okButton = BookingEditScene.getOkButton();
        okButton.setOnAction(e -> handle_okButton());
    }


    //metoda za onemogućavanje gumba u slučaju da nema slobodnih mjesta
    public static void setAvailableSeat() {

        try{
            String flight_route = routeBox.getValue();
            String flight_date = departureDatePicker.getValue().toString();

            for (FlightTable f : FlightTableData.getFlightTableItems()) {
                if (f.getDepartureDate().equalsIgnoreCase(flight_date) &&
                        (f.getDepartureCity() + " -> " + f.getArrivalCity()).equalsIgnoreCase(flight_route)) {

                    for(Flight flight: FlightData.getFlight())
                        if(flight.getFlightID()==f.getFlightID()){
                            if(flight.getEconomyLeft() < 1) {
                                economyClass.setDisable(true);
                                economyClass.setSelected(false);
                            }
                            else {
                                economyClass.setDisable(false);
                                economyClass.setSelected(true);
                            }

                            if(flight.getCoachLeft() < 1) {
                                coachClass.setDisable(true);
                                coachClass.setSelected(false);
                            }
                            else {
                                coachClass.setDisable(false);
                            }

                            if(flight.getFirstClassLeft() < 1) {
                                firstClass.setDisable(true);
                                firstClass.setSelected(false);
                            }
                            else {
                                firstClass.setDisable(false);
                            }

                            break;
                        }

                    if (firstClass.isSelected())
                        priceLabelObs.setText(String.valueOf(f.getPrice() + f.getPrice() * 1 / 2) + " $.");
                    else if (coachClass.isSelected())
                        priceLabelObs.setText(String.valueOf(f.getPrice() + f.getPrice() * 1 / 4) + " $.");

                    else priceLabelObs.setText(String.valueOf(f.getPrice()) + " $.");

                    break;
                }
            }

        } catch(Exception e){}

    }


    public static void setPrice() {
        try{
            String flight_route = routeBox.getValue();
            String flight_date = departureDatePicker.getValue().toString();

            for (FlightTable f : FlightTableData.getFlightTableItems())
                if (f.getDepartureDate().equalsIgnoreCase(flight_date) &&
                        (f.getDepartureCity() + " -> " + f.getArrivalCity()).equalsIgnoreCase(flight_route)) {

                    if (firstClass.isSelected())
                        priceLabelObs.setText(String.valueOf(f.getPrice() + f.getPrice() * 1 / 2) + " $.");
                    else if (coachClass.isSelected())
                        priceLabelObs.setText(String.valueOf(f.getPrice() + f.getPrice() * 1 / 4) + " $.");

                    else priceLabelObs.setText(String.valueOf(f.getPrice()) + " $.");

                    break;
                }

        } catch(Exception e){}
    }

    public static void setBooking(BookingTable bTab, Booking b) {

        bookingTable = bTab;
        booking = b;


        boolean existing = false;

        for(Booking bk : BookingData.getBookings())
            if(bookingTable.getBookingID() == bk.getBookingID()) {
                booking = bk;
                existing = true;
                break;
            }

        if(existing) {
            Customer customer = new Customer();
            for (Customer c : CustomerData.getCustomers())
                if (booking.getCustomerID() == c.getCustomerID()) {
                    customer = c;
                    break;
                }

            FlightTable flight = new FlightTable();
            for (FlightTable f : FlightTableData.getFlightTableItems()) {
                if(booking.getFlightID() == f.getFlightID()) {
                    flight = f;
                    break;
                }
            }


            customerBox.setValue(customer.getFirstName() + " " + customer.getLastName());
            routeBox.setValue(flight.getDepartureCity() + " -> " + flight.getArrivalCity());
            setDatePicker();
            departureDatePicker.setValue(LocalDate.parse(flight.getDepartureDate()));


            if(booking.getFareClass().equalsIgnoreCase("first class"))
                firstClass.setSelected(true);
            else if(booking.getFareClass().equalsIgnoreCase("coach"))
                coachClass.setSelected(true);
            else
                economyClass.setSelected(true);

            setPrice();
        }
    }

    public static void handle_okButton() {
        if(isInputValid()) {

            String customer = customerBox.getValue();
            for (Customer c : CustomerData.getCustomers()) {
                if ((c.getFirstName() + " " + c.getLastName()).equalsIgnoreCase(customer)) {
                    booking.setCustomerID(c.getCustomerID());
                    break;
                }
            }

            String flight_route = routeBox.getValue();
            String flight_date = departureDatePicker.getValue().toString();
            for (FlightTable f : FlightTableData.getFlightTableItems()) {
                if (f.getDepartureDate().equalsIgnoreCase(flight_date) &&
                        (f.getDepartureCity() + " -> " + f.getArrivalCity()).equalsIgnoreCase(flight_route)) {
                    booking.setFlightID(f.getFlightID());
                    break;
                }
            }


            if (firstClass.isSelected())
                booking.setFareClass("First class");
            else if (coachClass.isSelected())
                booking.setFareClass("Coach");
            else booking.setFareClass("Economy");

            for (Flight f : FlightData.getFlight()) {
                if (f.getFlightID() == booking.getFlightID()) {
                    if (booking.getFareClass().equalsIgnoreCase("first class"))
                        f.setFirstClassLeft(f.getFirstClassLeft() - 1);
                    else if (booking.getFareClass().equalsIgnoreCase("coach"))
                        f.setCoachLeft(f.getCoachLeft() - 1);
                    else
                        f.setEconomyLeft(f.getEconomyLeft() - 1);
                    FlightData.updateFlight(f);
                    break;
                }
            }

            okPressed = true;
            BookingEditScene.getDialogStage().close();
        }
    }
    public static void handleClose(){
        okPressed = false;
        BookingEditScene.getDialogStage().close();
    }

    public static void setDatePicker() {
        ArrayList<String> flightDates = new ArrayList<>();
        if(!routeBox.getSelectionModel().isEmpty())
            try {
                for (FlightTable f : FlightTableData.getFlightTableItems()) {
                    String a = routeBox.getValue();
                    String b = f.getDepartureCity() + " -> " + f.getArrivalCity();
                    LocalDate d = LocalDate.parse(f.getDepartureDate());
                    LocalDate e = LocalDate.now();
                    boolean bb = flightAvailable(f);
                    if(routeBox.getValue().equalsIgnoreCase(f.getDepartureCity() + " -> " + f.getArrivalCity()) &&
                            LocalDate.parse(f.getDepartureDate()).isAfter(LocalDate.now()) &&
                            flightAvailable(f))
                        flightDates.add(f.getDepartureDate());
                }

                if (flightDates.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(MainControl.getWindow());
                    alert.setHeaderText("No flights available");
                    alert.setContentText("There are no flights available on this route");
                    alert.showAndWait();
                }

                final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {

                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");

                                for (int i = 0; i < flightDates.size(); i++)
                                    if (item.isEqual(LocalDate.parse(flightDates.get(i)))) {
                                        setDisable(false);
                                        setStyle("-fx-background-color: limegreen");
                                    }

                                if (item.isEqual(departureDatePicker.getValue()))
                                    setStyle("-fx-border-color: white");
                            }
                        };
                    }
                };

                departureDatePicker.setValue(LocalDate.parse(flightDates.get(0)));
                departureDatePicker.setDayCellFactory(dayCellFactory);
            }
            catch (Exception e) {
                departureDatePicker.setValue(null);
                priceLabelObs.setText("");
            }
    }
    public static boolean flightAvailable(FlightTable fTab) {
        boolean ok = false;

        for(Flight f : FlightData.getFlight()) {
            if (f.getFlightID() == fTab.getFlightID()) {
                if (f.getEconomyLeft() >0 || f.getCoachLeft() >0 || f.getFirstClassLeft() >0)
                    ok = true;
                break;
            }
        }

        return ok;
    }

    public static boolean isInputValid() {
        String error = "";

        if (customerBox == null || customerBox.getSelectionModel().isEmpty()) {
            error += "No customers selected\n";
        }
        if (routeBox == null || routeBox.getSelectionModel().isEmpty()) {
            error += "No route selected\n";
        }

        if (error.equalsIgnoreCase(""))
            return true;

        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(MainControl.getWindow());
            alert.setHeaderText("Invalid reservation!");
            alert.setContentText(error);
            alert.showAndWait();

            return false;
        }
    }

    public static Booking getBooking() {
        return booking;
    }

    public static boolean isOkPressed() {
        return okPressed;
    }

}
