import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-update-email',
  templateUrl: './update-email.component.html',
  styleUrls: ['./update-email.component.css']
})
export class UpdateEmailComponent implements OnInit {


  email: string = '';

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {

  }


  public updateEmail(): void {

    this.userService.updateProfessorEmail(this.email).subscribe(
      (resp) => {

        alert('Successfully updated the email!');
        localStorage.setItem("token", resp.jwt);
        this.router.navigate(["/"]);

      },
      
      (err) => {
        
        alert("Update unsuccessfull!");

        if((err.status === 400 || err.status === 404) && (err.error && err.error.msg)) {
            alert(err.error.msg);
        }

        if((err.status === 409) && (err.error && err.error.additionalInfo)) {
            alert(err.error.additionalInfo);
        }
      
        this.router.navigate(["/"]);

      }


    );


  }





}
