package com.example.airlinereservastionsystem_team_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookingPageForCustomerController implements Initializable {

    @FXML
    private DatePicker date;

    @FXML
    private TextField from;

    @FXML
    private Button logout;

    @FXML
    private Button searchFlight;

    @FXML
    private TextField to;


    @FXML
    protected void onButtonBookingPage(ActionEvent event) {
        try {
            // Input validation
            if (from.getText() == null || from.getText().trim().isEmpty() ||
                    to.getText() == null || to.getText().trim().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR, "Fill in all the fields properly...!");
                alert.show();
            } else {
                // Parse input values to the appropriate data types
                String flightNameValue = from.getText();
                String destinationValue = to.getText();
                // Database insertion
                Connection connection = DataBaseConnection.getConnection();
                try (PreparedStatement pst = connection.prepareStatement(
                        "INSERT INTO search(Departure1,Destination1) VALUES (?, ?)")) {

                    pst.setString(1, flightNameValue);
                    pst.setString(2, destinationValue);
                    pst.executeUpdate();
                    FXMLLoader customerBookingLoader = new FXMLLoader(getClass().getResource("FlightDisplay.fxml"));
                    Parent RootCustomerBooking = customerBookingLoader.load();
                    Stage curCustomerBooking = (Stage) searchFlight.getScene().getWindow();
                    curCustomerBooking.setScene(new Scene(RootCustomerBooking));
                    curCustomerBooking.setTitle("Booking Flight");
                } catch (IOException e) {
                    System.out.println(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchFlight.setOnAction(this::onButtonBookingPage);
        logout.setOnAction(this::onButtonLogout);
    }
}



