package org.university.notification_service.validators;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.university.notification_service.dtos.*;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class NotificationValidatorImpl implements NotificationValidator{

    private Gson gson;

    @Autowired
    public NotificationValidatorImpl(Gson gson) {
        this.gson = gson;
    }

    public void validateRegistrationActivationParameters(String definedParameters, ActivateRegistrationNotiDto data) {

        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> subjects = gson.fromJson(definedParameters, listType);

        if(subjects.contains("name") && data.getName() == null) {
            throw new RuntimeException("The parameters that are passed are not valid for this type of notification!");
        }

        if(subjects.contains("surname") && data.getSurname() == null) {
            throw new RuntimeException("The parameters that are passed are not valid for this type of notification!");
        }

        if(subjects.contains("activationLink") && data.getActivationLink() == null) {
            throw new RuntimeException("The parameters that are passed are not valid for this type of notification!");
        }

    }


    public void validateChangePasswordRequestParameters(String definedParameters, ChangePasswordNotiDto data) {

        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> subjects = gson.fromJson(definedParameters, listType);

        if(subjects.contains("username") && data.getUsername() == null) {
            throw new RuntimeException("The parameters that are passed are not valid for this type of notification!");
        }

        if(subjects.contains("confirmationLink") && data.getConfirmationLink() == null) {
            throw new RuntimeException("The parameters that are passed are not valid for this type of notification!");
        }


    }


    public void validateExamPeriodActivationParameters(String definedParameters, ExamPeriodActivatedNotiDto data, List<StudentUsernameAndEmailDto> usernameAndEmailDataList) {

        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> subjects = gson.fromJson(definedParameters, listType);

        if(subjects.contains("examPeriod") && data.getExamPeriod() == null) {
            throw new RuntimeException("The parameters that are passed are not valid for this type of notification!");
        }

        for(StudentUsernameAndEmailDto dto : usernameAndEmailDataList) {

            if(subjects.contains("username") && dto.getUsername() == null) {
                throw new RuntimeException("The parameters that are passed are not valid for this type of notification!");
            }

        }

    }


    public void validateGradeAddedParameters(String definedParameters, GradeInfoNotiDto data, StudentNameAndEmailDto nameAndEmailData) {

        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> subjects = gson.fromJson(definedParameters, listType);

        if(subjects.contains("courseName") && data.getCourseName() == null) {
            throw new RuntimeException("The parameters that are passed are not valid for this type of notification!");
        }

        if(subjects.contains("grade") && data.getGrade() == null) {
            throw new RuntimeException("The parameters that are passed are not valid for this type of notification!");
        }

        if(subjects.contains("examPoints") && data.getExamPoints() == null) {
            throw new RuntimeException("The parameters that are passed are not valid for this type of notification!");
        }

        if(subjects.contains("preExamScoreDtos") && data.getPreExamScoreDtos() == null) {
            throw new RuntimeException("The parameters that are passed are not valid for this type of notification!");
        }

        if(subjects.contains("name") && nameAndEmailData.getName() == null) {
            throw new RuntimeException("The parameters that are passed are not valid for this type of notification!");
        }

    }

}
