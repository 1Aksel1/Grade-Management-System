package org.university_grade_management_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university_grade_management_service.exceptions.StudentEnrollmentNotActiveException;
import org.university_grade_management_service.exceptions.StudentEnrollmentNotFoundException;
import org.university_grade_management_service.model.Course;
import org.university_grade_management_service.model.EnrollmentStatus;
import org.university_grade_management_service.model.StudentEnrollment;
import org.university_grade_management_service.repositories.StudentEnrollmentRepository;
import org.university_grade_management_service.services.StudentEnrollmentService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {

    private StudentEnrollmentRepository studentEnrollmentRepository;

    @Autowired
    public StudentEnrollmentServiceImpl(StudentEnrollmentRepository studentEnrollmentRepository) {
        this.studentEnrollmentRepository = studentEnrollmentRepository;
    }


    @Override
    public void isStudentEnrolled(String studentIndex, Course course) {

        Optional<StudentEnrollment> studentEnrollmentOptional = studentEnrollmentRepository.findByStudentIndexAndCourse(studentIndex, course);

        if(!studentEnrollmentOptional.isPresent()) {
            throw new StudentEnrollmentNotFoundException("Student is not enrolled for this course!");
        }

        if(!studentEnrollmentOptional.get().getStatus().equals(EnrollmentStatus.ACTIVE)) {
            throw new StudentEnrollmentNotActiveException("Student is not enrolled for this course!");
        }


    }

    public void setStatusToCompleted(Course course, String studentIndex) {
        studentEnrollmentRepository.setStatusToCompleted(EnrollmentStatus.COMPLETED, course, studentIndex);
    }

    @Override
    public List<StudentEnrollment> findAllActiveEnrollmentsForStudentIndex(String studentIndex) {
        return studentEnrollmentRepository.findAllByStudentIndexAndAndStatus(studentIndex, EnrollmentStatus.ACTIVE);
    }

}
