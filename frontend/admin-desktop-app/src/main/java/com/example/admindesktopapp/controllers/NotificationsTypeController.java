package com.example.admindesktopapp.controllers;

import com.example.admindesktopapp.dto.NotificationTypeDto;
import com.example.admindesktopapp.services.NotificationsTypeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

@Component
public class NotificationsTypeController {

    private NotificationsTypeService notificationsTypeService;

    private ObservableList<NotificationTypeDto> notificationTypeDtoObservableList;

    private NotificationTypeDto selectedNotification;

    @FXML
    private TableView<NotificationTypeDto> notificationTypeDtoTableView;

    @FXML
    private TableColumn<NotificationTypeDto, Long> idColumn;

    @FXML
    private TableColumn<NotificationTypeDto, String> typeNameColumn;

    @FXML
    private TableColumn<NotificationTypeDto, String> subjectColumn;

    @FXML
    private TableColumn<NotificationTypeDto, String> templateColumn;

    @FXML
    private TableColumn<NotificationTypeDto, String> parametersColumn;

    @FXML
    private TextField typeNameField;

    @FXML
    private TextField subjectField;

    @FXML
    private TextArea templateTextArea;

    @FXML
    private TextField parametersField;

    @FXML
    private TextField deleteIdField;

    @FXML
    private TextField createTypeNameField;

    @FXML
    private TextField createSubjectField;

    @FXML
    private TextArea createTemplateTextArea;

    @FXML
    private TextField createParametersField;

    @FXML
    private Label successLabel;

    @FXML
    private Label errorLabel;



    public void intializeData() {

        resetFieldsAndData();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeNameColumn.setCellValueFactory(new PropertyValueFactory<>("typeName"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        templateColumn.setCellValueFactory(new PropertyValueFactory<>("template"));
        parametersColumn.setCellValueFactory(new PropertyValueFactory<>("parameters"));


        try {


            List<NotificationTypeDto> notificationTypeDtosList = notificationsTypeService.getAllNotificationTypes();
            notificationTypeDtoObservableList = FXCollections.observableList(notificationTypeDtosList);

            notificationTypeDtoTableView.setItems(notificationTypeDtoObservableList);


        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error fetching the notification types!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }

        notificationTypeDtoTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, notificationTypeDto, t1) -> {

            if(notificationTypeDto != null) {
                selectedNotification = t1;
                populateForm();
            }

        });

        if (!notificationTypeDtoTableView.getItems().isEmpty() && notificationTypeDtoObservableList.get(0) != null) {
            notificationTypeDtoTableView.getSelectionModel().select(0);
            selectedNotification = notificationTypeDtoObservableList.get(0);
            notificationTypeDtoTableView.requestFocus();
        }

    }


    @Autowired
    public NotificationsTypeController(NotificationsTypeService notificationsTypeService) {
        this.notificationsTypeService = notificationsTypeService;
    }


    private void populateForm() {

        if(selectedNotification != null) {
            typeNameField.setText(selectedNotification.getTypeName());
            subjectField.setText(selectedNotification.getSubject());
            templateTextArea.setText(selectedNotification.getTemplate());
            parametersField.setText(selectedNotification.getParameters());
        }

    }

    private void resetFieldsAndData() {

        typeNameField.setText("");
        subjectField.setText("");
        templateTextArea.setText("");
        parametersField.setText("");

        createTypeNameField.setText("");
        createSubjectField.setText("");
        createTemplateTextArea.setText("");
        createParametersField.setText("");

        deleteIdField.setText("");

        successLabel.setText("");
        errorLabel.setText("");

    }


    @FXML
    public void updateNotification() {

        successLabel.setText("");
        errorLabel.setText("");

        if(selectedNotification == null) {
            errorLabel.setText("Please select a notification type from the table you want to update!");
            return;
        }

        try {

            NotificationTypeDto notificationTypeDto = notificationsTypeService.updateNotificationType(selectedNotification.getId(), typeNameField.getText(), subjectField.getText(), templateTextArea.getText(), parametersField.getText());
            successLabel.setText("Successfully updated the notification type!");

            NotificationTypeDto notificationTypeTarget = null;

            for(NotificationTypeDto notificationTypeElement : notificationTypeDtoObservableList) {

                if(notificationTypeElement.getId() == notificationTypeDto.getId()) {
                    notificationTypeTarget = notificationTypeElement;
                }

            }

            notificationTypeDtoObservableList.remove(notificationTypeTarget);
            notificationTypeDtoObservableList.add(notificationTypeDto);

            selectedNotification = notificationTypeDto;
            notificationTypeDtoTableView.refresh();

        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error updating the notification type!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }




    }


    @FXML
    public void createNotification() {

        successLabel.setText("");
        errorLabel.setText("");

        try {

            NotificationTypeDto notificationTypeDto = notificationsTypeService.createNotificationType(createTypeNameField.getText(), createSubjectField.getText(), createTemplateTextArea.getText(), createParametersField.getText());
            successLabel.setText("Successfully created the notification type!");

            notificationTypeDtoObservableList.add(notificationTypeDto);
            selectedNotification = notificationTypeDto;
            notificationTypeDtoTableView.refresh();

            createTypeNameField.setText("");
            createSubjectField.setText("");
            createTemplateTextArea.setText("");
            createParametersField.setText("");

        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error creating the notification type!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }


    }

    @FXML
    public void deleteNotification() {

        successLabel.setText("");
        errorLabel.setText("");

        try {

            Long id = Long.parseLong(deleteIdField.getText());

            notificationsTypeService.deleteNotificationType(id);
            successLabel.setText("Successfully deleted the type notification!");

            NotificationTypeDto removedNotificationType = null;

            for(NotificationTypeDto notificationTypeDto : notificationTypeDtoObservableList) {

                if(notificationTypeDto.getId() == id) {
                    removedNotificationType = notificationTypeDto;
                }

            }

            notificationTypeDtoObservableList.remove(removedNotificationType);


        } catch (NumberFormatException e) {

            errorLabel.setText("Please enter a number value!");

        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error deleting the notification type!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }

        deleteIdField.setText("");

    }





}
