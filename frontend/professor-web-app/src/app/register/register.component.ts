import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterProfessorDto } from '../model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registrationPending: boolean = false;

  public registerProfessorDto: RegisterProfessorDto = {

    name : '',
    surname: '',
    username: '',
    password: '',
    email: '',
    telephoneNumber: '',
    dateOfBirth: '',
    hireDate: '',
    subjects: ''

  }

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  public resendActivationEmail(): void {

    this.userService.resendActivationEmail().subscribe(
      (resp) => {

        if(resp.message) {
          alert(resp.message);
        }

    },

    (err) => {

      alert("Operation unsuccessfull!");

      if((err.status === 400 || err.status === 404) && (err.error && err.error.msg)) {
          alert(err.error.msg);
      }

      if((err.status === 409) && (err.error && err.error.additionalInfo)) {
          alert(err.error.additionalInfo);
      }

    }
    )


  }

  public register(): void {

    this.userService.registerProfessor(this.registerProfessorDto).subscribe(
      (resp) => {

          alert('Registration successfull!');

          if(resp.message) {
            alert(resp.message);
            this.registrationPending = true;
          }

      },

      (err) => {

        alert("Registration unsuccessfull!");

        if((err.status === 400 || err.status === 404) && (err.error && err.error.msg)) {
            alert(err.error.msg);
        }

        if((err.status === 409) && (err.error && err.error.additionalInfo)) {
            alert(err.error.additionalInfo);
        }



      }

    )


  }


}
