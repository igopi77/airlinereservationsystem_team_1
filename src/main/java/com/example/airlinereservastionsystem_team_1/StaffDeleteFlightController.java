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
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class StaffDeleteFlightController implements Initializable{

    @FXML
    private Button delete;

    @FXML
    private TextField flightName;

    @FXML
    private TextField flightNo;

    @FXML
    private Button logout;
    @FXML
    protected void onButtonDeleteFlight(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Flight");
        dialog.setHeaderText(null);
        dialog.setContentText("Please confirm your Flight else it will not retrived:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(flightIdToDelete -> {
            // Validate that the entered value is an integer
            try {
                int flightId = Integer.parseInt(flightIdToDelete);

                // Perform deletion in the database
                Connection connection = DataBaseConnection.getConnection();
                try (PreparedStatement pst = connection.prepareStatement("DELETE FROM flight WHERE FlightId = ?")) {
                    pst.setInt(1, flightId);
                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Flight deleted successfully!");
                        successAlert.show();
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Flight ID not found!");
                        errorAlert.show();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            } catch (NumberFormatException e) {
                // Handle the case where the user entered a non-integer value for flight ID
                Alert alert = new Alert(Alert.AlertType.ERROR, "Enter a valid integer for Flight ID...!");
                alert.show();
            }
        });
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
        delete.setOnAction(this::onButtonDeleteFlight);
        logout.setOnAction(this::onButtonLogout);
    }

}
