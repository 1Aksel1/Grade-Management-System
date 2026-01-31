package com.example.admindesktopapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DashboardController {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab updateProfileTab;

    @FXML
    private Tab updateEmailTab;

    @FXML
    private Tab updatePasswordTab;

    @FXML
    private Tab notificationsTypeTab;

    @FXML
    private Tab savedNotificationsTab;

    @FXML
    private Tab examPeriodsTab;

    private UpdateProfileController updateProfileController;
    private NotificationsTypeController notificationsTypeController;
    private SavedNotificationController savedNotificationController;
    private UpdateEmailController updateEmailController;
    private UpdatePasswordController updatePasswordController;
    private ExamPeriodController examPeriodController;

    @Autowired
    public DashboardController(UpdateProfileController updateProfileController, SavedNotificationController savedNotificationController, UpdateEmailController updateEmailController, UpdatePasswordController updatePasswordController, ExamPeriodController examPeriodController, NotificationsTypeController notificationsTypeController) {
        this.updateProfileController = updateProfileController;
        this.savedNotificationController = savedNotificationController;
        this.updateEmailController = updateEmailController;
        this.updatePasswordController = updatePasswordController;
        this.examPeriodController = examPeriodController;
        this.notificationsTypeController = notificationsTypeController;
    }

    @FXML
    public void initialize() {

        this.updateProfileTab.setOnSelectionChanged(event -> {

            if(updateProfileTab.isSelected()) {
                updateProfileController.initializeData();
            }


        });

        this.savedNotificationsTab.setOnSelectionChanged(event -> {

            if(savedNotificationsTab.isSelected()) {
                savedNotificationController.initializeData();
            }


        });

        this.updateEmailTab.setOnSelectionChanged(event -> {

            if(updateEmailTab.isSelected()) {
                updateEmailController.intializeData();
            }


        });


        this.updatePasswordTab.setOnSelectionChanged(event -> {

            if(updatePasswordTab.isSelected()) {
                updatePasswordController.intializeData();
            }

        });


        this.examPeriodsTab.setOnSelectionChanged(event -> {

            if(examPeriodsTab.isSelected()) {
                examPeriodController.initializeData();
            }

        });

        this.notificationsTypeTab.setOnSelectionChanged(event -> {

            if(notificationsTypeTab.isSelected()) {
                notificationsTypeController.intializeData();
            }

        });


    }




}
