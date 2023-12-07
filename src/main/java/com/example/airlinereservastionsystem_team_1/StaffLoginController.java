package com.example.airlinereservastionsystem_team_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StaffLoginController implements Initializable {

    @FXML
    private Button login;

    @FXML
    private TextField password;

    @FXML
    private TextField username;
    @FXML
    protected void onButtonStaffLoginPage(ActionEvent event) {
        try {
            FXMLLoader StaffLoginLoader = new FXMLLoader(getClass().getResource("FlightUpdateStaff.fxml"));
            Parent RootStaffLogin = StaffLoginLoader.load();
            Stage curStaffLogin = (Stage) login.getScene().getWindow();
            curStaffLogin.setScene(new Scene(RootStaffLogin));
            curStaffLogin.setTitle("Staff Home");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login.setOnAction(this::onButtonStaffLoginPage);
    }
}
