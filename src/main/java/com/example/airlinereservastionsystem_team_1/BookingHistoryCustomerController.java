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
import java.util.Optional;
import java.util.ResourceBundle;

public class BookingHistoryCustomerController implements Initializable {

    @FXML
    private TableView<Ticket> ticketTableView;

    @FXML
    private TableColumn<Ticket, Integer> ticketIdColumn;

    @FXML
    private TableColumn<Ticket, String> noOfPersonColumn;

    @FXML
    private TableColumn<Ticket, String> emailIdColumn;

    @FXML
    private TableColumn<Ticket, String> nameColumn;

    @FXML
    private TableColumn<Ticket, String> phoneNumberColumn;
    @FXML
    private Button logout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the columns
        ticketIdColumn.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
        noOfPersonColumn.setCellValueFactory(new PropertyValueFactory<>("noOfPerson"));
        emailIdColumn.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        // Fetch data from the database and populate the table
        updateTableView();
    }

    private void updateTableView() {
        ObservableList<Ticket> tickets = FXCollections.observableArrayList();

        // Fetch data from the ticket table
        Connection connection = DataBaseConnection.getConnection();
        try {
            String query = "SELECT * FROM ticket";
            try (PreparedStatement pst = connection.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Ticket ticket = new Ticket(
                            rs.getInt("ticketId"),
                            rs.getString("noOfPerson"),
                            rs.getString("emailId"),
                            rs.getString("name"),
                            rs.getString("phoneNumber")
                    );
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ticketTableView.setItems(tickets);
    }
    @FXML
    protected void onButtonDeleteTicket(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cancel Ticket");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter the ticket id : ");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(flightIdToDelete -> {
            // Validate that the entered value is an integer
            try {
                int flightId = Integer.parseInt(flightIdToDelete);

                // Perform deletion in the database
                Connection connection = DataBaseConnection.getConnection();
                try (PreparedStatement pst = connection.prepareStatement("DELETE FROM ticket WHERE ticketId = ?")) {
                    pst.setInt(1, flightId);
                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "ticket cancelled successfully!");
                        successAlert.show();
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Ticket ID not found!");
                        errorAlert.show();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            } catch (NumberFormatException e) {
                // Handle the case where the user entered a non-integer value for flight ID
                Alert alert = new Alert(Alert.AlertType.ERROR, "Enter a valid integer for Ticket ID...!");
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
}
