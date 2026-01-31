package com.example.admindesktopapp.controllers;

import com.example.admindesktopapp.dto.SingleMessageDto;
import com.example.admindesktopapp.services.UpdateService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Text;

@Component
public class UpdatePasswordController {


    private UpdateService updateService;

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField newPasswordCheckField;

    @FXML
    private TextField confirmField;

    @FXML
    private Label successLabel;

    @FXML
    private Label errorLabel;



    @Autowired
    public UpdatePasswordController(UpdateService updateService) {
        this.updateService = updateService;
    }


    public void intializeData() {

        oldPasswordField.setText("");
        newPasswordField.setText("");
        newPasswordCheckField.setText("");
        confirmField.setText("");

        errorLabel.setText("");
        successLabel.setText("");

    }

    @FXML
    public void updatePassword() {

        successLabel.setText("");
        errorLabel.setText("");

        try {

            SingleMessageDto singleMessageDto = updateService.updatePassword(oldPasswordField.getText(), newPasswordField.getText(), newPasswordCheckField.getText());
            successLabel.setText(singleMessageDto.getMessage());

        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error updating the password!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }

    }

    @FXML
    public void confirmPasswordChange() {

        successLabel.setText("");
        errorLabel.setText("");

        try {

            SingleMessageDto singleMessageDto = updateService.confirmPasswordChange(confirmField.getText());
            successLabel.setText(singleMessageDto.getMessage());

        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error with confirming the password update!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }



    }



}
