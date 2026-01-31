import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UpdateProfessorDto } from '../model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  public updateProfessorDto: UpdateProfessorDto = {

    name: '',
    surname: '',
    username: '',
    email: '',
    telephoneNumber: '',
    dateOfBirth: '',
    hireDate: '',
    subjects: ''

  }


  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {

    this.userService.getProfessorUpdateDto().subscribe(

      (resp) => {
          this.updateProfessorDto = resp;
      },

      (err) => {

        alert(err.msg);
      }

    )

  }


  public updateProfessor(): void {


    this.userService.updateProfessor(this.updateProfessorDto).subscribe(

      (resp) => {
        alert("Update successfull!");
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
