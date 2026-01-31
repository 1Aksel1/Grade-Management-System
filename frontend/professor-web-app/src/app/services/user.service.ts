import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ChangePasswordDto, Jwt, Notification, RegisterProfessorDto, SingleMessageDto, UpdateEmailDto, UpdateProfessorDto } from '../model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private api: string = environment.professorBffApi;

  constructor(private httpClient: HttpClient) { }


  public login(email: string, password: string): Observable<Jwt> {

    const url: string = `${this.api}/login`;

    return this.httpClient.post<Jwt>(url, {
      email: email,
      password: password
    });

  }

  public getProfessorUpdateDto(): Observable<UpdateProfessorDto> {

    const url: string = `${this.api}/profile`;

    return this.httpClient.get<UpdateProfessorDto>(url, {
      headers: {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`
      }
    })

  }

  public updateProfessor(updateProfessorDto: UpdateProfessorDto): Observable<UpdateProfessorDto> {

    const url: string = `${this.api}/profile`;

    return this.httpClient.put<UpdateProfessorDto>(url, updateProfessorDto, {
      headers: {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`,
        'Content-Type': 'application/json',
      }
    })

  }

  public updateProfessorEmail(email: string): Observable<Jwt> {

    const url: string = `${this.api}/profile/changeEmail`;

    let updateEmailDto: UpdateEmailDto = {
      email: email
    }

    return this.httpClient.put<Jwt>(url, updateEmailDto, {
      headers: {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`,
        'Content-Type': 'application/json',
      }
    });

  }

  public updateProfessorPassword(changePasswordDto: ChangePasswordDto): Observable<SingleMessageDto> {

    const url: string = `${this.api}/profile/changePassword`;

    return this.httpClient.post<SingleMessageDto>(url, changePasswordDto, {
      headers : {
        "Authorization" : `Bearer ${localStorage.getItem("token")}`,
        'Content-Type': 'application/json',
      }
    })

  }

  public registerProfessor(registerProfessorDto: RegisterProfessorDto): Observable<SingleMessageDto> {

    const url: string = `${this.api}/register`;

      return this.httpClient.post<SingleMessageDto>(url, registerProfessorDto, {
        headers: {
          'Content-Type': 'application/json',
        },
        withCredentials: true
      })

  }

  public resendActivationEmail(): Observable<SingleMessageDto> {

    const url: string = `${this.api}/resendActivationEmail`;

    return this.httpClient.get<SingleMessageDto>(url, {
      withCredentials: true
    })

  }


}
