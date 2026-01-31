package org.university.notification_service.services;

import org.university.notification_service.dtos.ActivateRegistrationNotiDto;
import org.university.notification_service.dtos.ChangePasswordNotiDto;
import org.university.notification_service.dtos.ExamPeriodActivatedNotiDto;
import org.university.notification_service.dtos.GradeInfoNotiDto;

public interface ProcessNotificationService {

    public void processRegistrationActivation(ActivateRegistrationNotiDto data);

    public void processChangePasswordRequest(ChangePasswordNotiDto data);

    public void processExamPeriodActivation(ExamPeriodActivatedNotiDto data);

    public void processGradeAdded(GradeInfoNotiDto data);



}
