package com.example.admindesktopapp.controllers;

import com.example.admindesktopapp.AdminApplication;
import com.example.admindesktopapp.loaders.SpringFXMLLoader;
import com.example.admindesktopapp.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginController {

    private AuthService authService;
    private SpringFXMLLoader springFXMLLoader;

    @Autowired
    public LoginController(AuthService authService, SpringFXMLLoader springFXMLLoader) {
        this.authService = authService;
        this.springFXMLLoader = springFXMLLoader;
    }

    public LoginController() {
    }

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    public void loginAction(ActionEvent actionEvent) {

           try {

               authService.login(emailField.getText(), passwordField.getText());

               openDashboardWindow();
               ((Stage) emailField.getScene().getWindow()).close();

            } catch (RuntimeException e) {

                if(e.getMessage() == null) {
                    errorLabel.setText("Invalid credentials!");
                } else {
                    errorLabel.setText(e.getMessage());
                }

               emailField.clear();
               passwordField.clear();

            }


    }


    private void openDashboardWindow()  {

        try {

            FXMLLoader fxmlLoader = springFXMLLoader.load("dashboard-view.fxml");

            Stage stage = new Stage();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(fxmlLoader.load(), 1240, 1020));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Error loading the dashboard!");
        }



    }


}
