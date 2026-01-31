import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseCompleteInfo, PreExamObligation } from '../model';
import { GradeManagementService } from '../services/grade-management.service';

@Component({
  selector: 'app-single-course',
  templateUrl: './single-course.component.html',
  styleUrls: ['./single-course.component.css']
})
export class SingleCourseComponent implements OnInit {


  course: CourseCompleteInfo = {

    id : 0,
    name: '',
    studyProgram: '',
    ects: 0,
    preExamObligations: [],
    preExamObligationScores: [],
    studentEnrollments: [],
    grades: []

  }

  peoName: string = '';
  peoMaxPoints: string = '';

  peosStudentIndex: string = '';
  peosName: string = '';
  peosPointsScored: string = '';

  gradeStudentIndex: string = '';
  gradePointsScored: string = '';
  gradeExamPeriod: string = '';


  constructor(private gradeManagementService: GradeManagementService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    const id: number = parseInt(<string>this.route.snapshot.paramMap.get("id"));

    this.gradeManagementService.getSingleCourse(id).subscribe(

      (resp) => {
        this.course = resp;
      },

      (err) => {

        if((err.status === 400 || err.status === 404) && (err.error && err.error.msg)) {
          alert(err.error.msg);
        }

      }

    )

  }


  createPreExamObligation(): void {

    const id: number = parseInt(<string>this.route.snapshot.paramMap.get("id"));

    this.gradeManagementService.createPreExamObligation(id, this.peoName, this.peoMaxPoints).subscribe(

      (resp) => {
        this.course.preExamObligations.push(resp);
      },

      (err) => {

        alert("Unsuccessfull!");

        if((err.status === 400 || err.status === 404 || err.status === 422 || err.status === 503) && (err.error && err.error.msg)) {
            alert(err.error.msg);
        }

      }


    )

    this.peoName = '';
    this.peoMaxPoints = '';


  }

  createPreExamObligationScore(): void {

    const id: number = parseInt(<string>this.route.snapshot.paramMap.get("id"));

    this.gradeManagementService.createPreExamObligationScore(id, this.peosStudentIndex, this.peosName, this.peosPointsScored).subscribe(

      (resp) => {
        this.course.preExamObligationScores.push(resp);
      },

      (err) => {

        alert("Unsuccessfull!");

        if((err.status === 400 || err.status === 404 || err.status === 422 || err.status === 503) && (err.error && err.error.msg)) {
            alert(err.error.msg);
        }


      }

    )

    this.peosStudentIndex = '';
    this.peosName = '';
    this.peosPointsScored = '';


  }

  createGrade(): void {

    const id: number = parseInt(<string>this.route.snapshot.paramMap.get("id"));

    this.gradeManagementService.createGrade(id, this.gradeStudentIndex, this.gradePointsScored, this.gradeExamPeriod).subscribe(

      (resp) => {
        this.course.grades.push(resp);
      },

      (err) => {

        alert("Unsuccessfull!");

        if((err.status === 400 || err.status === 404 || err.status === 422 || err.status === 503) && (err.error && err.error.msg)) {
            alert(err.error.msg);
        }

      }


    )

    this.gradeStudentIndex = '';
    this.gradePointsScored = '';
    this.gradeExamPeriod = '';

  }


}
