import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Notification } from '../model';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private api: string = environment.professorBffApi;

  constructor(private httpClient: HttpClient) { }

  public searchNotifications(activation_email: boolean, password_change: boolean, exam_period_activated: boolean,
    grade_added: boolean, fromDate: string, toDate: string): Observable<Array<Notification>> {

      let url: string = `${this.api}/savedNotifications`;
      let queryString = this.buildSearchNotificationsQueryString(activation_email, password_change, exam_period_activated, grade_added, fromDate, toDate);

      url += queryString;

      return this.httpClient.get<Array<Notification>>(url, {
        headers: {
          "Authorization" : `Bearer ${localStorage.getItem("token")}`
        }
      })

    }
    

  private buildSearchNotificationsQueryString(activation_email: boolean, password_change: boolean, exam_period_activated: boolean,
    grade_added: boolean, fromDate: string, toDate: string ): string {


      let queryString = '?';

      if(activation_email) {
        queryString += `activation_email=true&`;
      }

      if(password_change) {
        queryString += `password_change=true&`;
      }

      if(exam_period_activated) {
        queryString += `exam_period_activated=true&`;
      }

      if(grade_added) {
        queryString += `grade_added=true&`;
      }

      if(fromDate !== '') {
        queryString += `fromDate=${fromDate}&`;
      }

      if(toDate !== '') {
        queryString += `toDate=${toDate}&`;
      }

      queryString = queryString.slice(0, queryString.length - 1);

      return queryString;

    }



}
