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
public class StaffAddFlightController implements Initializable {

    @FXML
    private Button add;

    @FXML
    private Button logout;

    @FXML
    protected void onButtonAddFlight(ActionEvent event) {
        try {
            FXMLLoader addFlightLoader = new FXMLLoader(getClass().getResource("FlightUpdateStaff.fxml"));
            Parent RootAddFlight = addFlightLoader.load();
            Stage curAddFlight = (Stage) add.getScene().getWindow();
            curAddFlight.setScene(new Scene(RootAddFlight));
            curAddFlight.setTitle("Staff home");
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
        add.setOnAction(this::onButtonAddFlight);
        logout.setOnAction(this::onButtonLogout);
    }

}
