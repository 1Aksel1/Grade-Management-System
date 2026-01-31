import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { ChangePasswordDto } from '../model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent implements OnInit {

  public changePasswordDto: ChangePasswordDto = {

    oldPassword: '',
    newPassword: '',
    newPasswordCheck: ''

  }


  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  public updatePassword(): void {

    if(this.changePasswordDto.newPassword !== this.changePasswordDto.newPasswordCheck) {
      
      alert('The passwords don\'t match');
      this.changePasswordDto.newPassword = '';
      this.changePasswordDto.newPasswordCheck = '';
      return;

    }

    this.userService.updateProfessorPassword(this.changePasswordDto).subscribe(
      (resp) => {

        if(resp.message){
          alert(resp.message);
        }

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
    )




  }

}
