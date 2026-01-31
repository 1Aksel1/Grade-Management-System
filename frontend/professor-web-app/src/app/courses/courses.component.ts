import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CourseGeneralInfoDto } from '../model';
import { GradeManagementService } from '../services/grade-management.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  courses: Array<CourseGeneralInfoDto> = Array<CourseGeneralInfoDto>();

  constructor(private gradeManagementService: GradeManagementService, private router: Router) { }

  ngOnInit(): void {

    this.gradeManagementService.getProfessorCourses().subscribe(

      (resp) => {
        this.courses = resp;
      },

      (err) => {

        if((err.status === 400 || err.status === 404) && (err.error && err.error.msg)) {
          alert(err.error.msg);
        }


      }
      
    )

  }


  redirectToSingleCoursePage(courseId: number): void {

    this.router.navigate([`/courses/${courseId}`]);

  }






}
