package com.example.airlinereservastionsystem_team_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateNewAcoountCustomerController implements Initializable{

    @FXML
    private PasswordField cPassword;
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button signUpForCustomer;

    @FXML
    private TextField userName;
    @FXML
    private Button cancel;
    @FXML
    public void onButtonCreateCustomer(ActionEvent event) {
        if (userName.getText() == null || userName.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter username without whitespace ...!");
            alert.show();
        } else if (password.getText() == null || password.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter password without whitespace...!");
            alert.show();
        } else if (phoneNumber.getText() == null || phoneNumber.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter contact number without whitespace...!");
            alert.show();
        } else if (!(password.getText()).equals(cPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter Confirm password as same as password ...!");
            alert.show();
        } else if (firstName.getText() == null || firstName.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter FirstName without whitespace...!");
            alert.show();
        } else if (lastName.getText() == null || lastName.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter LastName without whitespace...!");
            alert.show();
        } else {
            PreparedStatement pst = null;
            ResultSet rs=null;
            Connection connection =DataBaseConnection.getConnection();
            try {
                pst = connection.prepareStatement("INSERT INTO Customer (Customer_id,passwor,FirstName,LastName,PhoneNumber) values(?,?,?,?,?)");
                pst.setString(1, userName.getText());
                pst.setString(2, password.getText());
                pst.setString(3, firstName.getText());
                pst.setString(4, lastName.getText());
                pst.setString(5, phoneNumber.getText());
                pst.executeUpdate();
                FXMLLoader NewcustomerLoginLoader = new FXMLLoader(getClass().getResource("CustomerLoginPage.fxml"));
                Parent loginRootCustomerLogin = NewcustomerLoginLoader.load();
                Stage curLoginCustomer = (Stage) signUpForCustomer.getScene().getWindow();
                curLoginCustomer.setScene(new Scene(loginRootCustomerLogin));
                curLoginCustomer.setTitle("Login page");
            } catch (IOException e) {
                System.out.println(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    protected void onButtonBack(ActionEvent event) {
        try {
            FXMLLoader customerBack = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Parent RootCustomerBack = customerBack.load();
            Stage curCustomerBack = (Stage) cancel.getScene().getWindow();
            curCustomerBack.setScene(new Scene(RootCustomerBack));
            curCustomerBack.setTitle("Airline Reservation System");

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpForCustomer.setOnAction(this::onButtonCreateCustomer);
    }

}
