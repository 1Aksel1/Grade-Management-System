package com.example.admindesktopapp.controllers;

import com.example.admindesktopapp.dto.UpdateAdminDto;
import com.example.admindesktopapp.services.UpdateService;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UpdateProfileController {

    private UpdateService updateService;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField dateOfBirth;

    @FXML
    private Label successLabel;

    @FXML
    private Label errorLabel;



    @Autowired
    public UpdateProfileController(UpdateService updateService) {
        this.updateService = updateService;
    }

    public void initializeData() {

        successLabel.setText("");
        errorLabel.setText("");

        try {

            UpdateAdminDto updateAdminDto = updateService.getUpdateAdminDto();
            populateFields(updateAdminDto);

        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error fetching the data!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }


    }

    private void populateFields(UpdateAdminDto updateAdminDto) {

        nameField.setText(updateAdminDto.getName());
        surnameField.setText(updateAdminDto.getSurname());
        usernameField.setText(updateAdminDto.getUsername());
        emailField.setText(updateAdminDto.getEmail());
        telephoneField.setText(updateAdminDto.getTelephoneNumber());
        dateOfBirth.setText(updateAdminDto.getDateOfBirth());

    }

    @FXML
    public void updateProfile() {

        successLabel.setText("");
        errorLabel.setText("");

        try {

            UpdateAdminDto updateAdminDto = updateService.updateAdmin(nameField.getText(), surnameField.getText(), usernameField.getText(), emailField.getText(), telephoneField.getText(), dateOfBirth.getText());
            populateFields(updateAdminDto);
            successLabel.setText("Successfully updated the profile!");

        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error when executing the update operation!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }


    }






}
