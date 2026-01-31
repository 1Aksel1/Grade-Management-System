import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  email: string = '';
  password: string = '';

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {

  }

  login(): void {

    this.validateInput();

    this.userService.login(this.email, this.password).subscribe(

      (resp) => {
        localStorage.setItem("token", resp.jwt);
        this.router.navigate(["/"]);
      },

      (err) => {
        alert('Wrong credentials or the service is unavailable!');
      }

    )

    this.email = '';
    this.password = '';

  }


  validateInput(): void {

    if(this.email === '' || this.password === ''){
      this.email = '';
      this.password = '';
      alert('Both fields must be submited!');
      return;
    }


  }



}
