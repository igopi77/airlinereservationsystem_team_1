package com.example.airlinereservastionsystem_team_1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FlightDisplayController implements Initializable {
    @FXML
    private TableColumn<Flight, String> departure;

    @FXML
    private TableColumn<Flight, String> destination;

    @FXML
    private Button book;

    @FXML
    private TableColumn<Flight, Integer> duration;

    @FXML
    private TableColumn<Flight, Integer> flightNO;

    @FXML
    private TableColumn<Flight, String> flightName;

    @FXML
    private TableColumn<Flight, String> flightTime;

    @FXML
    private Button logout;

    @FXML
    private TableColumn<Flight, Integer> prize;

    @FXML
    private TableView<Flight> tableview;

    @FXML
    private Button view;

    @FXML
    private Button back;

    @FXML
    private TextField username;

    @FXML
    private Button ok;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the columns
        flightNO.setCellValueFactory(new PropertyValueFactory<>("flightNo"));
        flightName.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        flightTime.setCellValueFactory(new PropertyValueFactory<>("flightTime"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        prize.setCellValueFactory(new PropertyValueFactory<>("price"));
        departure.setCellValueFactory(new PropertyValueFactory<>("departure"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));

        // Fetch data from the database and populate the table
        updateTableView();
    }

    private void updateTableView() {
        ObservableList<Flight> flights = FXCollections.observableArrayList();

        // Fetch data from the flight table based on values in the search table
        Connection connection = DataBaseConnection.getConnection();
        try {
            // Retrieve departure1 and destination1 values from the search table
            String searchQuery = "SELECT Departure1, Destination1 FROM search";
            try (PreparedStatement searchPst = connection.prepareStatement(searchQuery);
                 ResultSet searchRs = searchPst.executeQuery()) {

                if (searchRs.next()) {
                    String departure = searchRs.getString("Departure1");
                    String destination = searchRs.getString("Destination1");

                    // Use departure and destination values to filter flights
                    String flightQuery = "SELECT * FROM flight WHERE Departure = ? AND Destination = ?";
                    try (PreparedStatement flightPst = connection.prepareStatement(flightQuery)) {
                        flightPst.setString(1, departure);
                        flightPst.setString(2, destination);

                        try (ResultSet rs = flightPst.executeQuery()) {
                            while (rs.next()) {
                                Flight flight = new Flight(
                                        rs.getInt("FlightId"),
                                        rs.getString("FlightName"),
                                        rs.getString("time"),
                                        rs.getInt("Duration"),
                                        rs.getInt("Price"),
                                        rs.getString("departure"),
                                        rs.getString("destination")
                                );
                                flights.add(flight);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableview.setItems(flights);
    }

    @FXML
    protected void onButtonBook(ActionEvent event) {
        // Get the selected flight from the table view
        Flight selectedFlight = tableview.getSelectionModel().getSelectedItem();
        String userName = username.getText();

        if (selectedFlight == null) {
            // No row selected, show an alert or handle the case appropriately
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a flight to book.");
            alert.show();
        } else {
            // A row is selected, proceed with booking
            Connection connection = DataBaseConnection.getConnection();

            if (connection != null) {
                try {
                    // Specify the table name for booking
                    String tableName = "book";

                    // Create a SQL statement to insert the selected flight into the book table
                    String query = "INSERT INTO " + tableName + " (FlightNo, FlightName, FlightTime, timing, Prize,CustomerId,Departure,Destination) VALUES (?, ?, ?, ?, ?,?,?,?)";

                    // Prepare and execute the statement
                    try (PreparedStatement pst = connection.prepareStatement(query)) {
                        pst.setInt(1, selectedFlight.getFlightNo());
                        pst.setString(2, selectedFlight.getFlightName());
                        pst.setString(3, selectedFlight.getFlightTime());
                        pst.setInt(4, selectedFlight.getDuration());
                        pst.setInt(5, selectedFlight.getPrice());
                        pst.setString(6,userName);
                        pst.setString(7,selectedFlight.getDeparture());
                        pst.setString(8,selectedFlight.getDestination());

                        pst.executeUpdate();

                        String customerId = userName;
                        // Load the TicketPage.fxml when the booking is successful
                        FXMLLoader customerBook = new FXMLLoader(getClass().getResource("TicketPage.fxml"));
                        Parent rootCustomerBook = customerBook.load();
                        Stage curCustomerBook =  (Stage) book.getScene().getWindow();
                        curCustomerBook.setScene(new Scene(rootCustomerBook));

                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                } finally {
                    try {
                        // Close the connection after usage
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



    @FXML
    protected void onButtonLogout(ActionEvent event) {
        try {
            FXMLLoader customerLogout = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Parent RootCustomerLogout = customerLogout.load();
            Stage curCustomerLogout = (Stage) logout.getScene().getWindow();
            curCustomerLogout.setScene(new Scene(RootCustomerLogout));
            curCustomerLogout.setTitle("Airline Reservation System");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    @FXML
    protected void onTruncateButtonClick(ActionEvent event) {
        // Get a connection to your database
        Connection connection = DataBaseConnection.getConnection();

        if (connection != null) {
            try {
                // Specify the table name you want to truncate
                String tableName = "search";

                // Create a SQL statement to truncate the table
                String query = "TRUNCATE TABLE " + tableName;

                // Prepare and execute the statement
                try (PreparedStatement pst = connection.prepareStatement(query)) {
                    pst.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error truncating table: " + e.getMessage());
                alert.show();
            }
        }
    }

    @FXML
    protected void onButtonBack(ActionEvent event) {
        try {
            FXMLLoader customerBack = new FXMLLoader(getClass().getResource("FlightUpdateStaff.fxml"));
            Parent RootCustomerBack = customerBack.load();
            Stage curCustomerBack = (Stage) back.getScene().getWindow();
            curCustomerBack.setScene(new Scene(RootCustomerBack));
            curCustomerBack.setTitle("Airline Reservation System");

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    @FXML
    protected void onButtonView(ActionEvent event) {
        // Call the updateTableView method to refresh the table view
        updateTableView();
    }
}
