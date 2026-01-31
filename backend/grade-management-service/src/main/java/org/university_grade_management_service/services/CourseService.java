package org.university_grade_management_service.services;

import org.university_grade_management_service.dtos.CourseGeneralInfoDto;
import org.university_grade_management_service.model.*;

import java.util.List;

public interface CourseService {

    public Course findCourseByName(String courseName);

    public Course findCourseById(Long id);

    public Course validateProfessorCourse(String authHeader, Long courseId);

    public List<CourseGeneralInfoDto> findAllProfessorCourses(String authHeader);

    public List<CourseGeneralInfoDto> findAllStudentActiveCourses(String authHeader);

    public CourseGeneralInfoDto getCourseGeneralInfoForId(String authHeader, Long courseId);

    public List<PreExamObligation> getPreExamObligationsForCourseId(String authHeader, Long courseId);

    public List<PreExamObligationScore> getPreExamObligationsScoresForCourseId(String authHeader, Long courseId);

    public List<StudentEnrollment> getStudentEnrollmentsForCourseId(String authHeader, Long courseId);

    public List<Grade> getGradesForCourseId(String authHeader, Long courseId);


}
