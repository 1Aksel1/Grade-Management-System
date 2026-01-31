package org.university_grade_management_service.services;

import org.university_grade_management_service.model.Course;
import org.university_grade_management_service.model.StudentEnrollment;

import java.util.List;

public interface StudentEnrollmentService {

    public void isStudentEnrolled(String studentIndex, Course course);

    public void setStatusToCompleted(Course course, String studentIndex);

    public List<StudentEnrollment> findAllActiveEnrollmentsForStudentIndex(String studentIndex);

}
