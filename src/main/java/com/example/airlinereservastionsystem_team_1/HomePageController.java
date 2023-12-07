package com.example.airlinereservastionsystem_team_1;

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


public class HomePageController {

    @FXML
    private Button loginButtonCustomer;

    @FXML
    private Button loginButtonStaff;
    @FXML
    protected void onHandleButtonSignupStaff(){
        try{
            FXMLLoader staffLoginLoader=new FXMLLoader(getClass().getResource("StaffLogin.fxml"));
            Parent loginRootStaff= staffLoginLoader.load();
            Stage curLoginStaff = (Stage)loginButtonStaff.getScene().getWindow();
            curLoginStaff.setScene(new Scene(loginRootStaff));
            curLoginStaff.setTitle("Staff login page");
        }
        catch(IOException e){
            e.printStackTrace();
}
    }
    @FXML
    protected void onHandleButtonSignupCustomer(){
        try{
            FXMLLoader customerLoginLoader=new FXMLLoader(getClass().getResource("CustomerLoginPage.fxml"));
            Parent loginRootCustomer= customerLoginLoader.load();
            Stage curLoginCustomer = (Stage)loginButtonCustomer.getScene().getWindow();
            curLoginCustomer.setScene(new Scene(loginRootCustomer));
            curLoginCustomer.setTitle("Customer login page");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
