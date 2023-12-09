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
    private Button cancel;
    @FXML
    public void onClickLogin(ActionEvent event){
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection connection = DataBaseConnection.getConnection();
        try {
            pst = connection.prepareStatement("SELECT * FROM customer WHERE customer_id = ? AND passwor = ?");
            pst.setString(1,username.getText());
            pst.setString(2,password.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                try{
                    String user = username.getText();
                    FXMLLoader customerLoginloader = new FXMLLoader(getClass().getResource("BookingPageForCustomer.fxml"));
                    Parent rootCustomerLogin = (Parent) customerLoginloader.load();
                    Stage curCustomerLogin= (Stage) button_login.getScene().getWindow();
                    curCustomerLogin.setScene(new Scene(rootCustomerLogin));
                    curCustomerLogin.setTitle("Flight Booking");
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
        button_login.setOnAction(this::onClickLogin);
        newaccount.setOnAction(this::onButtonNewAccountCustomer);
    }
}

