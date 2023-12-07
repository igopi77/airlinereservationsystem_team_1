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

public class CustomerLoginPageController  implements Initializable {

    @FXML
    private Button button_login;

    @FXML
    private Button newaccount;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    protected void onButtonLoginCustomer(ActionEvent event){
        try{
            FXMLLoader customerLoginLoader=new FXMLLoader(getClass().getResource("BookingPageForCustomer.fxml"));
            Parent loginRootCustomerLogin= customerLoginLoader.load();
            Stage curLoginCustomer = (Stage) button_login.getScene().getWindow();
            curLoginCustomer.setScene(new Scene(loginRootCustomerLogin));
            curLoginCustomer.setTitle("Booking Flight");
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    @FXML
    protected void onButtonNewAccountCustomer(ActionEvent event){
        try{
            FXMLLoader customerNewLoginLoader=new FXMLLoader(getClass().getResource("CreateNewAcoountCustomer.fxml"));
            Parent loginRootCustomerNewLogin= customerNewLoginLoader.load();
            Stage curNewLoginCustomer = (Stage)newaccount.getScene().getWindow();
            curNewLoginCustomer.setScene(new Scene(loginRootCustomerNewLogin));
            curNewLoginCustomer.setTitle("New customer");
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(this::onButtonLoginCustomer);
        newaccount.setOnAction(this::onButtonNewAccountCustomer);
    }
}

