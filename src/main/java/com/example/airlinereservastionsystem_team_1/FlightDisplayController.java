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

public class FlightDisplayController implements Initializable{

    @FXML
    private Button book;

    @FXML
    private Button logout;
    @FXML
    protected void onButtonFlightDisplay(ActionEvent event) {
        try {
            FXMLLoader ticketPageLoader = new FXMLLoader(getClass().getResource("TicketPage.fxml"));
            Parent RootTicketPage = ticketPageLoader.load();
            Stage curTicketPage = (Stage) book.getScene().getWindow();
            curTicketPage.setScene(new Scene(RootTicketPage));
            curTicketPage.setTitle("Booking Flight");
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
        book.setOnAction(this::onButtonFlightDisplay);
        logout.setOnAction(this::onButtonLogout);
    }
}
