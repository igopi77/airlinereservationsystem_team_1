package com.example.airlinereservastionsystem_team_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StaffLoginController implements Initializable {

    @FXML
    private Button login;

    @FXML
    private TextField password;

    @FXML
    private TextField username;
    public void onButtonStaffLoginPage(ActionEvent event){
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection connection = DataBaseConnection.getConnection();
        try {
            pst = connection.prepareStatement("SELECT * FROM staff WHERE username = ? AND password = ?");
            pst.setString(1,username.getText());
            pst.setString(2,password.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                try{
                    FXMLLoader staffLoginLoader = new FXMLLoader(getClass().getResource("FlightUpdateStaff.fxml"));
                    Parent rootStaffLogin = (Parent) staffLoginLoader.load();
                    Stage curStaffLogin= (Stage) login.getScene().getWindow();
                    curStaffLogin.setScene(new Scene(rootStaffLogin));
                    curStaffLogin.setTitle("Flight Booking");
                }
                catch (IOException e){
                    System.out.println(e);
                }
            }
            else{
                Alert error = new Alert(Alert.AlertType.ERROR,"Invalid username/Password...!", ButtonType.OK);
                error.show();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login.setOnAction(this::onButtonStaffLoginPage);
    }
}
