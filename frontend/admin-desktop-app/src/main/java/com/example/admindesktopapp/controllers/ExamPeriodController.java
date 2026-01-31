package com.example.admindesktopapp.controllers;

import com.example.admindesktopapp.dto.ExamPeriod;
import com.example.admindesktopapp.dto.SingleMessageDto;
import com.example.admindesktopapp.services.GradeManagementService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class ExamPeriodController {

    private GradeManagementService gradeManagementService;

    private ObservableList<ExamPeriod> examPeriodObservableList;

    @FXML
    private ComboBox examPeriodComboBox;

    @FXML
    private TableView<ExamPeriod> examPeriodTableView;

    @FXML
    private TableColumn<ExamPeriod, String> periodColumn;

    @FXML
    private TableColumn<ExamPeriod, Boolean> statusColumn;

    @FXML
    private Label successLabel;

    @FXML
    private Label errorLabel;


    @Autowired
    public ExamPeriodController(GradeManagementService gradeManagementService) {
        this.gradeManagementService = gradeManagementService;
    }


    public void initializeData() {

        examPeriodComboBox.getSelectionModel().clearSelection();

        successLabel.setText("");
        errorLabel.setText("");

        try {

            periodColumn.setCellValueFactory(new PropertyValueFactory<>("period"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

            List<ExamPeriod> examPeriods = gradeManagementService.getAllExamPeriods();

            examPeriodObservableList = FXCollections.observableList(examPeriods);

            examPeriodComboBox.setItems(FXCollections.observableArrayList(
                    "JANUARY", "FEBRUARY", "JUNE", "JULY", "AUGUST", "SEPTEMBER"
            ));

            examPeriodTableView.setItems(examPeriodObservableList);

        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error fetching the exam periods!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }

    }


    @FXML
    public void activateExamPeriod() {

        successLabel.setText("");
        errorLabel.setText("");

        String examPeriod = (String) examPeriodComboBox.getValue();

        try {

            SingleMessageDto singleMessageDto = gradeManagementService.activateExamPeriod(examPeriod);
            successLabel.setText(singleMessageDto.getMessage());

            if(!singleMessageDto.getMessage().contains("already")) {

               Iterator<ExamPeriod> iterator = examPeriodObservableList.listIterator();

                while (iterator.hasNext()) {
                    ExamPeriod tempExamPeriod = iterator.next();

                    if(tempExamPeriod.getPeriod().equals(examPeriod)){
                        tempExamPeriod.setStatus(true);
                    }

                }

                examPeriodTableView.refresh();

            }

        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error fetching the exam periods!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }




    }

    @FXML
    public void deactivateExamPeriod() {

        successLabel.setText("");
        errorLabel.setText("");

        String examPeriod = (String) examPeriodComboBox.getValue();

        try {

            SingleMessageDto singleMessageDto = gradeManagementService.deactivateExamPeriod(examPeriod);
            successLabel.setText(singleMessageDto.getMessage());

            if(!singleMessageDto.getMessage().contains("already")) {

                Iterator<ExamPeriod> iterator = examPeriodObservableList.listIterator();

                while (iterator.hasNext()) {
                    ExamPeriod tempExamPeriod = iterator.next();

                    if(tempExamPeriod.getPeriod().equals(examPeriod)){
                        tempExamPeriod.setStatus(false);
                    }

                }

                examPeriodTableView.refresh();

            }

        } catch (RuntimeException e) {

            if(e.getMessage() == null) {
                errorLabel.setText("Error fetching the exam periods!");
            } else {
                errorLabel.setText(e.getMessage());
            }

        }


    }



}
