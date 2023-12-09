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

public class FlightUpdateStaffController implements Initializable  {

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private Button logout;
    @FXML
    protected void onButtonAddStaff(ActionEvent event) {
        try {
            FXMLLoader addStaffLoader = new FXMLLoader(getClass().getResource("StaffAddFlight.fxml"));
            Parent RootAddStaff = addStaffLoader.load();
            Stage curAddStaff = (Stage) add.getScene().getWindow();
            curAddStaff.setScene(new Scene(RootAddStaff));
            curAddStaff.setTitle("Booking Flight");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    @FXML
    protected void onButtonDeleteStaff(ActionEvent event) {
        try {
            FXMLLoader deleteStaffLoader = new FXMLLoader(getClass().getResource("StaffDeleteFlight.fxml"));
            Parent RootDeleteStaff = deleteStaffLoader.load();
            Stage curDeleteStaff = (Stage) delete.getScene().getWindow();
            curDeleteStaff.setScene(new Scene(RootDeleteStaff));
            curDeleteStaff.setTitle("Booking Flight");
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
        add.setOnAction(this::onButtonAddStaff);
        delete.setOnAction(this::onButtonDeleteStaff);
        logout.setOnAction(this::onButtonLogout);
    }

}
