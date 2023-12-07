package com.example.airlinereservastionsystem_team_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookingPageForCustomerController implements Initializable {

    @FXML
    private Button logout;

    @FXML
    private Button searchFlight;

    @FXML
    protected void onButtonBookingPage(ActionEvent event) {
        try {
            FXMLLoader customerBookingLoader = new FXMLLoader(getClass().getResource("FlightDisplay.fxml"));
            Parent RootCustomerBooking = customerBookingLoader.load();
            Stage curCustomerBooking = (Stage) searchFlight.getScene().getWindow();
            curCustomerBooking.setScene(new Scene(RootCustomerBooking));
            curCustomerBooking.setTitle("Booking Flight");
        } catch (IOException e) {
            System.out.println(e);
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



