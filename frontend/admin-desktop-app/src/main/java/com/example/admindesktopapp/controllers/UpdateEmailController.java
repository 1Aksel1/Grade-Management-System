package com.example.admindesktopapp.controllers;

import com.example.admindesktopapp.services.UpdateService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateEmailController {

    private UpdateService updateService;

    @FXML
    private TextField emailField;

    @FXML
    private Label successLabel;

    @FXML
    private Label errorLabel;

    @Autowired
    public UpdateEmailController(UpdateService updateService) {
        this.updateService = updateService;
    }

    public void intializeData() {

        emailField.setText("");

        successLabel.setText("");
        errorLabel.setText("");

    }

    @FXML
    public void updateEmail() {

        successLabel.setText("");
        errorLabel.setText("");

        try {

            updateService.updateEmail(emailField.getText());
            successLabel.setText("Successfully updated the email!");

        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error updating the email! Email must not be already taken!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }

    }







}
