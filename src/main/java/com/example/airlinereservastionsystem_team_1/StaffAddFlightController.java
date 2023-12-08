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
public class StaffAddFlightController implements Initializable {

    @FXML
    private Button add;

    @FXML
    private DatePicker date;

    @FXML
    private TextField departure;

    @FXML
    private TextField destination;

    @FXML
    private TextField duration;

    @FXML
    private TextField flightName;

    @FXML
    private TextField flightNo;

    @FXML
    private TextField flightTime;

    @FXML
    private Button logout;

    @FXML
    private TextField price;

    @FXML
    protected void onButtonAddFlight(ActionEvent event) {
        try {
            // Input validation
            if (flightName.getText() == null || flightName.getText().trim().isEmpty() ||
                    flightNo.getText() == null || flightNo.getText().trim().isEmpty() ||
                    destination.getText() == null || destination.getText().trim().isEmpty() ||
                    departure.getText() == null || departure.getText().trim().isEmpty() ||
                    date.getValue() == null ||
                    flightTime.getText() == null || flightTime.getText().trim().isEmpty() ||
                    price.getText() == null || price.getText().trim().isEmpty() ||
                    duration.getText() == null || duration.getText().trim().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR, "Fill in all the fields properly...!");
                alert.show();

            } else {
                // Parse input values to the appropriate data types
                String flightNameValue = flightName.getText();
                int flightNoValue = Integer.parseInt(flightNo.getText());
                String destinationValue = destination.getText();
                String departureValue = departure.getText();
                String dateValue = date.getValue().toString();
                String flightTimeValue = flightTime.getText();
                int priceValue = Integer.parseInt(price.getText());
                int durationValue = Integer.parseInt(duration.getText());

                // Database insertion
                Connection connection = DataBaseConnection.getConnection();
                try (PreparedStatement pst = connection.prepareStatement(
                        "INSERT INTO flight(FlightName, Departure, Destination, date, time, price, FlightId, duration) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

                    pst.setString(1, flightNameValue);
                    pst.setString(2, departureValue);
                    pst.setString(3, destinationValue);
                    pst.setString(4, dateValue);
                    pst.setString(5, flightTimeValue);
                    pst.setInt(6, priceValue);
                    pst.setInt(7, flightNoValue);
                    pst.setInt(8, durationValue);

                    pst.executeUpdate();

                    // Redirect to the next scene
                    FXMLLoader addFlightLoader = new FXMLLoader(getClass().getResource("FlightUpdateStaff.fxml"));
                    Parent RootAddFlight = addFlightLoader.load();
                    Stage curAddFlight = (Stage) add.getScene().getWindow();
                    curAddFlight.setScene(new Scene(RootAddFlight));
                    curAddFlight.setTitle("Staff home");

                } catch (IOException e) {
                    System.out.println(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (NumberFormatException e) {
            // Handle the case where the user entered a non-integer value for flightNo, price, or duration
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter valid integer values for Flight No, Price, and Duration...!");
            alert.show();
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
        add.setOnAction(this::onButtonAddFlight);
        logout.setOnAction(this::onButtonLogout);
    }

}
