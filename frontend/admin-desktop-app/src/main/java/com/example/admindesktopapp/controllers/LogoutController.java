package com.example.admindesktopapp.controllers;

import com.example.admindesktopapp.AdminApplication;
import com.example.admindesktopapp.loaders.SpringFXMLLoader;
import com.example.admindesktopapp.services.AuthService;
import com.example.admindesktopapp.util.JwtUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LogoutController {

    private AuthService authService;
    private SpringFXMLLoader springFXMLLoader;

    @Autowired
    public LogoutController(AuthService authService, SpringFXMLLoader springFXMLLoader) {
        this.authService = authService;
        this.springFXMLLoader = springFXMLLoader;
    }

    public LogoutController() {
    }

    @FXML
    private Label label;

    @FXML
    private Label errorLabel;

    @FXML
    public void logoutAction(ActionEvent actionEvent) {

        openLoginWindow();
        ((Stage) label.getScene().getWindow()).close();
        authService.logout();

    }

    private void openLoginWindow()  {

        try {

            FXMLLoader fxmlLoader = springFXMLLoader.load("login-view.fxml");

            Stage stage = new Stage();
            stage.setTitle("Admin Application");
            stage.setScene(new Scene(fxmlLoader.load(), 1260, 1020));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Error loading the dashboard!");
        }

    }



}
