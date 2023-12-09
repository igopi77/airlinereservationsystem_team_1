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

public class TicketPageController implements Initializable {

    @FXML
    private TextField bookingId;

    @FXML
    private Button confirmTicket;

    @FXML
    private TextField email;

    @FXML
    private Button logout;

    @FXML
    private TextField name;

    @FXML
    private TextField noOfTicket;

    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button home;

    @FXML
    protected void onButtonConfirm(ActionEvent event) {
        if (name.getText() == null || name.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter correctly ...!");
            alert.show();
        } else if (email.getText() == null || email.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter correctly ...!");
            alert.show();
        }
        else if (username.getText() == null || username.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter correctly ...!");
            alert.show();
        }
        else if (password.getText() == null || password.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter correctly ...!");
            alert.show();
        }
        else if (phoneNumber.getText() == null || phoneNumber.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter correctly ...!");
            alert.show();
        }
        else if (noOfTicket.getText() == null || noOfTicket.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter correctly ...!");
            alert.show();
        }
        else {
            PreparedStatement pst = null;
            ResultSet rs=null;
            Connection connection =DataBaseConnection.getConnection();
            try {
                pst = connection.prepareStatement("INSERT INTO ticket (noOfPerson, EmailID, Name, PhoneNumber,CustomerId) VALUES (?, ?, ?, ?,?)");
                pst.setString(1, noOfTicket.getText());
                pst.setString(2, email.getText());
                pst.setString(3, name.getText());
                pst.setString(4, phoneNumber.getText());
                pst.setString(5,username.getText());
                pst.executeUpdate();
                //Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ticket booked successfully it will be directed to booking history");
                //alert.show();
                FXMLLoader bookingConfirmLoader = new FXMLLoader(getClass().getResource("BookingHistoryCustomer.fxml"));
                Parent RootBookingConfirm = bookingConfirmLoader.load();
                Stage curBookingConfirm = (Stage) confirmTicket.getScene().getWindow();
                curBookingConfirm.setScene(new Scene(RootBookingConfirm));
                curBookingConfirm.setTitle("Booking History");
            } catch (IOException e) {
                System.out.println(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    protected void onButtonLogout(ActionEvent event) {
        try {
                String user = username.getText();
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
    protected void onButtonBack(ActionEvent event) {
        try {
            FXMLLoader customerBack = new FXMLLoader(getClass().getResource("FlightUpdateStaff.fxml"));
            Parent RootCustomerBack = customerBack.load();
            Stage curCustomerBack = (Stage) home.getScene().getWindow();
            curCustomerBack.setScene(new Scene(RootCustomerBack));
            curCustomerBack.setTitle("Airline Reservation System");

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmTicket.setOnAction(this::onButtonConfirm);
        logout.setOnAction(this::onButtonLogout);
    }
}