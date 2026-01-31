package org.university_grade_management_service.services;

import org.university_grade_management_service.model.Period;
import org.university_grade_management_service.model.PreExamObligationScore;

import java.util.List;

public interface SendNotificationsService {

    public void sendExamPeriodActiveNotification(Period examPeriod);

    public void sendGradeInfoNotification(String studentIndex, String courseName, Integer grade, Integer examPoints, List<PreExamObligationScore> preExamObligationScores);


}
