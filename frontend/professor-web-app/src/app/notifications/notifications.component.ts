import { Component, OnInit } from '@angular/core';
import { Notification } from '../model';
import { NotificationService } from '../services/notification.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {

  accountActivation: boolean = true;
  passwordChange: boolean = true;
  examPeriodActivation: boolean = true;
  gradeAdded: boolean = true;
  dateFrom: string = '';
  toDate: string = '';
  notifications: Array<Notification> = new Array<Notification>();

  constructor(private notificationService: NotificationService) { }

  ngOnInit(): void {

    this.notificationService.searchNotifications(this.accountActivation, this.passwordChange, this.examPeriodActivation, this.gradeAdded,
      '', '').subscribe(

       (resp) => {
         this.notifications = resp;
         console.log(this.notifications);
       },

       (err) => {

         if((err.status === 400 || err.status === 404) && (err.error && err.error.msg)) {
           alert(err.error.msg);
         }


       })


  }

  formatDate(date: string): string {

    if(!date) {
      return '';
    }
    const dateObject = new Date(date);
    return dateObject.toISOString();

  }


  searchNotifications(): void {

    const dateFromISO = this.formatDate(this.dateFrom);
    const toDateISO = this.formatDate(this.toDate);

    this.notificationService.searchNotifications(this.accountActivation, this.passwordChange, this.examPeriodActivation, this.gradeAdded,
       dateFromISO, toDateISO).subscribe(

        (resp) => {
          this.notifications = resp;
          console.log(this.notifications);
        },

        (err) => {

          if((err.status === 400 || err.status === 404) && (err.error && err.error.msg)) {
            alert(err.error.msg);
          }


        })


  }



}
