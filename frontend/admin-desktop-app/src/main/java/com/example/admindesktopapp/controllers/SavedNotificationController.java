package com.example.admindesktopapp.controllers;

import com.example.admindesktopapp.dto.SavedNotification;
import com.example.admindesktopapp.services.SavedNotificationsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SavedNotificationController {

    private SavedNotificationsService savedNotificationsService;

    @FXML
    private CheckBox accountActivationCheckBox;

    @FXML
    private CheckBox passwordChangeCheckBox;

    @FXML
    private CheckBox examPeriodActivationCheckBox;

    @FXML
    private CheckBox gradeAddedCheckBox;

    @FXML
    private TextField emailField;

    @FXML
    private TextField dateFromField;

    @FXML
    private TextField dateToField;

    @FXML
    private TableView<SavedNotification> notificationTableView;

    @FXML
    private TableColumn<SavedNotification, String> emailColumn;

    @FXML
    private TableColumn<SavedNotification, String> subjectColumn;

    @FXML
    private TableColumn<SavedNotification, String> contentColumn;

    @FXML
    private TableColumn<SavedNotification, String> sentColumn;

    @FXML
    private Label errorLabel;

    @Autowired
    public SavedNotificationController(SavedNotificationsService savedNotificationsService) {
        this.savedNotificationsService = savedNotificationsService;
    }

    public void initializeData() {

        resetFieldValues();

        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        sentColumn.setCellValueFactory(new PropertyValueFactory<>("sentTimeAndDate"));

        try {

            List<SavedNotification> savedNotifications = savedNotificationsService.searchSavedNotifications(accountActivationCheckBox.isSelected(), passwordChangeCheckBox.isSelected(), examPeriodActivationCheckBox.isSelected(), gradeAddedCheckBox.isSelected(), emailField.getText(), dateFromField.getText(), dateToField.getText());

            ObservableList<SavedNotification> observableList = FXCollections.observableList(savedNotifications);
            notificationTableView.setItems(observableList);

        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error fetching the data!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }

    }

    @FXML
    public void searchSavedNotifications() {

        errorLabel.setText("");

        try {

            List<SavedNotification> savedNotifications = savedNotificationsService.searchSavedNotifications(accountActivationCheckBox.isSelected(), passwordChangeCheckBox.isSelected(), examPeriodActivationCheckBox.isSelected(), gradeAddedCheckBox.isSelected(), emailField.getText(), dateFromField.getText(), dateToField.getText());

            ObservableList<SavedNotification> observableList = FXCollections.observableList(savedNotifications);
            notificationTableView.setItems(observableList);

        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error fetching the data!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }


    }



    private void resetFieldValues() {

        accountActivationCheckBox.setSelected(true);
        passwordChangeCheckBox.setSelected(true);
        examPeriodActivationCheckBox.setSelected(true);
        gradeAddedCheckBox.setSelected(true);

        emailField.setText("");
        dateFromField.setText("");
        dateToField.setText("");

        errorLabel.setText("");

    }



}
