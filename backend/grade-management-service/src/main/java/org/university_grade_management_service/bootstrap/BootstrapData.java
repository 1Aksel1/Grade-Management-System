package org.university_grade_management_service.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.university_grade_management_service.model.*;
import org.university_grade_management_service.repositories.*;

import java.util.ArrayList;
import java.util.List;


@Component
public class BootstrapData implements CommandLineRunner {

    private CourseRepository courseRepository;
    private PreExamObligationRepository preExamRepository;
    private PreExamObligationScoreRepository preExamScoreRepository;
    private ExamPeriodRepository examPeriodRepository;
    private StudentEnrollmentRepository studentEnrollmentRepository;
    private GradeRepository gradeRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public BootstrapData(PasswordEncoder passwordEncoder, CourseRepository courseRepository, PreExamObligationRepository preExamRepository, PreExamObligationScoreRepository preExamScoreRepository, ExamPeriodRepository examPeriodRepository, StudentEnrollmentRepository studentEnrollmentRepository, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.preExamRepository = preExamRepository;
        this.preExamScoreRepository = preExamScoreRepository;
        this.examPeriodRepository = examPeriodRepository;
        this.gradeRepository = gradeRepository;
        this.studentEnrollmentRepository = studentEnrollmentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Course> courseList = new ArrayList<>();
        List<StudentEnrollment> studentEnrollmentList = new ArrayList<>();
        List<PreExamObligation> preExamObligationList = new ArrayList<>();
        List<PreExamObligationScore> preExamObligationScoreList = new ArrayList<>();
        List<ExamPeriod> examPeriodList = new ArrayList<>();
        List<Grade> gradeList = new ArrayList<>();


        Course c1 = new Course("Mikroservisne aplikacije", "RI", 6);
        Course c2 = new Course("Razvoj servisa u oblaku", "RN", 6);
        Course c3 = new Course("Arhitektura računara", "RI", 6);
        Course c4 = new Course("Napredno web programiranje", "RI", 6);

        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseList.add(c4);


        StudentEnrollment se1 = new StudentEnrollment("RI26/19", EnrollmentStatus.ACTIVE, c1);
        StudentEnrollment se2 = new StudentEnrollment("RI26/19", EnrollmentStatus.COMPLETED, c2);
        StudentEnrollment se3 = new StudentEnrollment("RI26/19", EnrollmentStatus.COMPLETED, c3);
        StudentEnrollment se4 = new StudentEnrollment("RI26/19", EnrollmentStatus.ACTIVE, c4);
        StudentEnrollment se5 = new StudentEnrollment("RI27/19", EnrollmentStatus.ACTIVE, c1);
        StudentEnrollment se6 = new StudentEnrollment("RI27/19", EnrollmentStatus.ACTIVE, c2);
        StudentEnrollment se7 = new StudentEnrollment("RI27/19", EnrollmentStatus.ACTIVE, c3);
        StudentEnrollment se8 = new StudentEnrollment("RI27/19", EnrollmentStatus.ACTIVE, c4);
        StudentEnrollment se9 = new StudentEnrollment("RI111/20", EnrollmentStatus.COMPLETED, c1);
        StudentEnrollment se10 = new StudentEnrollment("RI111/20", EnrollmentStatus.ACTIVE, c2);
        StudentEnrollment se11 = new StudentEnrollment("RI111/20", EnrollmentStatus.COMPLETED, c3);
        StudentEnrollment se12 = new StudentEnrollment("RI111/20", EnrollmentStatus.WITHDRAWN, c4);
        StudentEnrollment se13 = new StudentEnrollment("RI10/21", EnrollmentStatus.COMPLETED, c1);
        StudentEnrollment se14 = new StudentEnrollment("RI10/21", EnrollmentStatus.COMPLETED, c2);
        StudentEnrollment se15 = new StudentEnrollment("RI10/21", EnrollmentStatus.ACTIVE, c3);
        StudentEnrollment se16 = new StudentEnrollment("RI10/21", EnrollmentStatus.ACTIVE, c4);

        studentEnrollmentList.add(se1);
        studentEnrollmentList.add(se2);
        studentEnrollmentList.add(se3);
        studentEnrollmentList.add(se4);
        studentEnrollmentList.add(se5);
        studentEnrollmentList.add(se6);
        studentEnrollmentList.add(se7);
        studentEnrollmentList.add(se8);
        studentEnrollmentList.add(se9);
        studentEnrollmentList.add(se10);
        studentEnrollmentList.add(se11);
        studentEnrollmentList.add(se12);
        studentEnrollmentList.add(se13);
        studentEnrollmentList.add(se14);
        studentEnrollmentList.add(se15);
        studentEnrollmentList.add(se16);


        PreExamObligation p1 = new PreExamObligation("kolokvijum1", 28, c1);
        PreExamObligation p2 = new PreExamObligation("kolokvijum2", 30, c1);
        PreExamObligation p3 = new PreExamObligation("test", 15, c2);
        PreExamObligation p4 = new PreExamObligation("kolokvijum", 35, c2);
        PreExamObligation p5 = new PreExamObligation("projekat", 40, c3);
        PreExamObligation p6 = new PreExamObligation("test", 10, c3);
        PreExamObligation p7 = new PreExamObligation("kolokvijum", 50, c4);

        preExamObligationList.add(p1);
        preExamObligationList.add(p2);
        preExamObligationList.add(p3);
        preExamObligationList.add(p4);
        preExamObligationList.add(p5);
        preExamObligationList.add(p6);
        preExamObligationList.add(p7);


        PreExamObligationScore ps1 = new PreExamObligationScore("kolokvijum1", "RI26/19", 28, c1);
        PreExamObligationScore ps3 = new PreExamObligationScore("kolokvijum2", "RI26/19", 15, c1);
        PreExamObligationScore ps4 = new PreExamObligationScore("kolokvijum1", "RI27/19", 25, c1);
        PreExamObligationScore ps5 = new PreExamObligationScore("kolokvijum1", "RI111/20", 28, c1);
        PreExamObligationScore ps6 = new PreExamObligationScore("kolokvijum2", "RI111/20", 29, c1);
        PreExamObligationScore ps7 = new PreExamObligationScore("kolokvijum1", "RI10/21", 20, c1);
        PreExamObligationScore ps9 = new PreExamObligationScore("kolokvijum2", "RI10/21", 17, c1);
        PreExamObligationScore ps10 = new PreExamObligationScore("test", "RI26/19", 11, c2);
        PreExamObligationScore ps11 = new PreExamObligationScore("kolokvijum", "RI26/19", 33, c2);
        PreExamObligationScore ps12 = new PreExamObligationScore("test", "RI27/19", 7, c2);
        PreExamObligationScore ps13 = new PreExamObligationScore("kolokvijum", "RI27/19", 20, c2);
        PreExamObligationScore ps14 = new PreExamObligationScore("test", "RI111/20", 14, c2);
        PreExamObligationScore ps15 = new PreExamObligationScore("kolokvijum", "RI111/20", 28, c2);
        PreExamObligationScore ps16 = new PreExamObligationScore("test", "RI10/21", 9, c2);
        PreExamObligationScore ps17 = new PreExamObligationScore("kolokvijum", "RI10/21", 34, c2);
        PreExamObligationScore ps18 = new PreExamObligationScore("projekat", "RI26/19", 40, c3);
        PreExamObligationScore ps19 = new PreExamObligationScore("test", "RI26/19", 4, c3);
        PreExamObligationScore ps20 = new PreExamObligationScore("projekat", "RI27/19", 25, c3);
        PreExamObligationScore ps21 = new PreExamObligationScore("test", "RI27/19", 7, c3);
        PreExamObligationScore ps22 = new PreExamObligationScore("projekat", "RI111/20", 39, c3);
        PreExamObligationScore ps23 = new PreExamObligationScore("test", "RI111/20", 10, c3);
        PreExamObligationScore ps24 = new PreExamObligationScore("projekat", "RI10/21", 17, c3);
        PreExamObligationScore ps25 = new PreExamObligationScore("test", "RI10/21", 2, c3);
        PreExamObligationScore ps26 = new PreExamObligationScore("kolokvijum", "RI26/19", 28, c4);
        PreExamObligationScore ps27 = new PreExamObligationScore("kolokvijum", "RI27/19", 35, c4);
        PreExamObligationScore ps28 = new PreExamObligationScore("kolokvijum", "RI10/21", 13, c4);

        preExamObligationScoreList.add(ps1);
        preExamObligationScoreList.add(ps3);
        preExamObligationScoreList.add(ps4);
        preExamObligationScoreList.add(ps5);
        preExamObligationScoreList.add(ps6);
        preExamObligationScoreList.add(ps7);
        preExamObligationScoreList.add(ps9);
        preExamObligationScoreList.add(ps10);
        preExamObligationScoreList.add(ps11);
        preExamObligationScoreList.add(ps12);
        preExamObligationScoreList.add(ps13);
        preExamObligationScoreList.add(ps14);
        preExamObligationScoreList.add(ps15);
        preExamObligationScoreList.add(ps16);
        preExamObligationScoreList.add(ps17);
        preExamObligationScoreList.add(ps18);
        preExamObligationScoreList.add(ps19);
        preExamObligationScoreList.add(ps20);
        preExamObligationScoreList.add(ps21);
        preExamObligationScoreList.add(ps22);
        preExamObligationScoreList.add(ps23);
        preExamObligationScoreList.add(ps24);
        preExamObligationScoreList.add(ps25);
        preExamObligationScoreList.add(ps26);
        preExamObligationScoreList.add(ps27);
        preExamObligationScoreList.add(ps28);


        ExamPeriod ep1 = new ExamPeriod(Period.JANUARY, true);
        ExamPeriod ep2 = new ExamPeriod(Period.FEBRUARY, true);
        ExamPeriod ep3 = new ExamPeriod(Period.JUNE, false);
        ExamPeriod ep4 = new ExamPeriod(Period.JULY, false);
        ExamPeriod ep5 = new ExamPeriod(Period.AUGUST, false);
        ExamPeriod ep6 = new ExamPeriod(Period.SEPTEMBER, false);

        examPeriodList.add(ep1);
        examPeriodList.add(ep2);
        examPeriodList.add(ep3);
        examPeriodList.add(ep4);
        examPeriodList.add(ep5);
        examPeriodList.add(ep6);


        Grade g1 = new Grade("RI111/20", 10, 40, Period.JANUARY, c1);
        Grade g2 = new Grade("RI10/21", 7, 30, Period.FEBRUARY, c1);
        Grade g3 = new Grade("RI26/19", 9, 40, Period.JANUARY, c2);
        Grade g4 = new Grade("RI10/21", 10, 50, Period.FEBRUARY, c2);
        Grade g5 = new Grade("RI26/19", 8, 34, Period.JANUARY, c3);
        Grade g6 = new Grade("RI111/20", 6, 5, Period.JANUARY, c3);

        gradeList.add(g1);
        gradeList.add(g2);
        gradeList.add(g3);
        gradeList.add(g4);
        gradeList.add(g5);
        gradeList.add(g6);


        courseRepository.saveAll(courseList);
        studentEnrollmentRepository.saveAll(studentEnrollmentList);
        preExamRepository.saveAll(preExamObligationList);
        preExamScoreRepository.saveAll(preExamObligationScoreList);
        examPeriodRepository.saveAll(examPeriodList);
        gradeRepository.saveAll(gradeList);



    }


}
