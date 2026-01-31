import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CourseCompleteInfo, CourseGeneralInfoDto, Grade, PreExamObligation, PreExamObligationScore, StudentEnrollment } from '../model';

@Injectable({
  providedIn: 'root'
})
export class GradeManagementService {

  private api: string = environment.professorBffApi;

  constructor(private httpClient: HttpClient) { }


  public getProfessorCourses(): Observable<Array<CourseGeneralInfoDto>> {

    const url = `${this.api}/professorCourses`;

    return this.httpClient.get<Array<CourseGeneralInfoDto>>(url, {
      headers: {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`
      }
    })

  }


  public getSingleCourse(id: number): Observable<CourseCompleteInfo> {

    const url = `${this.api}/courses/${id}`;

    return this.httpClient.get<CourseCompleteInfo>(url, {
      headers: {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`
      }
    })


  }

  public getCourseGeneralInfo(id: number): Observable<CourseGeneralInfoDto> {

    const url = `${this.api}/courses/generalInfo/${id}`;

    return this.httpClient.get<CourseGeneralInfoDto>(url, {
      headers: {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`
      }
    })

  }

  public getPreExamObligations(id: number): Observable<Array<PreExamObligation>> {

    const url = `${this.api}/courses/${id}/preExamObligations`;

    return this.httpClient.get<Array<PreExamObligation>>(url, {
      headers: {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`
      }
    })

  }

  public getPreExamObligationScores(id: number): Observable<Array<PreExamObligationScore>> {

    const url = `${this.api}/courses/${id}/preExamObligationsScores`;

    return this.httpClient.get<Array<PreExamObligationScore>>(url, {
      headers: {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`
      }
    })

  }


  public getStudentEnrollments(id: number): Observable<Array<StudentEnrollment>> {

    const url = `${this.api}/courses/${id}/studentEnrollments`;

    return this.httpClient.get<Array<StudentEnrollment>>(url, {
      headers: {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`
      }
    })

  }

  public getGrades(id: number): Observable<Array<Grade>> {

    const url = `${this.api}/courses/${id}/grades`;

    return this.httpClient.get<Array<Grade>>(url, {
      headers: {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`
      }
    })

  }






  public createPreExamObligation(id: number, name: string, maxPoints: string): Observable<PreExamObligation> {

    const url = `${this.api}/preExamObligation/course/${id}`;

    const body = {
      name: name,
      maxPoints: maxPoints
    }

    return this.httpClient.post<PreExamObligation>(url, body, {
      headers: {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`,
        'Content-Type': 'application/json',
      }
    });


  }


  public createPreExamObligationScore(id: number, studentIndex: string, name: string, pointsScored: string): Observable<PreExamObligationScore> {

    const url = `${this.api}/preExamObligationScore/course/${id}`;

    const body = {
      studentIndex: studentIndex,
      name: name,
      pointsScored: pointsScored
    }


    return this.httpClient.post<PreExamObligationScore>(url, body, {
      headers: {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`,
        'Content-Type': 'application/json',
      }
    });


  }


  public createGrade(id: number, studentIndex: string, pointsScored: string, examPeriod: string): Observable<Grade> {

    const url = `${this.api}/grade/course/${id}`;

    const body = {
      studentIndex: studentIndex,
      pointsScored: pointsScored,
      examPeriod: examPeriod
    }

    return this.httpClient.post<Grade>(url, body, {
      headers: {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`,
        'Content-Type': 'application/json',
      }
    });

  }



}
