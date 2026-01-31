package org.university.notification_service.validators;

import org.university.notification_service.dtos.*;

import java.util.List;

public interface NotificationValidator {

    public void validateRegistrationActivationParameters(String definedParameters, ActivateRegistrationNotiDto data);

    public void validateChangePasswordRequestParameters(String definedParameters, ChangePasswordNotiDto data);

    public void validateExamPeriodActivationParameters(String definedParameters, ExamPeriodActivatedNotiDto data, List<StudentUsernameAndEmailDto> usernameAndEmailDataList);

    public void validateGradeAddedParameters(String definedParameters, GradeInfoNotiDto data, StudentNameAndEmailDto nameAndEmailData);


}
