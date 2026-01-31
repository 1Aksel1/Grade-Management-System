import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { UpdateEmailComponent } from './update-email/update-email.component';
import { UpdatePasswordComponent } from './update-password/update-password.component';
import { ConfirmUpdatePasswordComponent } from './confirm-update-password/confirm-update-password.component';
import { ConfirmRegistrationComponent } from './confirm-registration/confirm-registration.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { CoursesComponent } from './courses/courses.component';
import { SingleCourseComponent } from './single-course/single-course.component';
import { PasswordChangeUnsuccessfulComponent } from './password-change-unsuccessful/password-change-unsuccessful.component';
import { RegistrationUnsuccessfulComponent } from './registration-unsuccessful/registration-unsuccessful.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    HeaderComponent,
    UpdateProfileComponent,
    UpdateEmailComponent,
    UpdatePasswordComponent,
    ConfirmUpdatePasswordComponent,
    ConfirmRegistrationComponent,
    NotificationsComponent,
    CoursesComponent,
    SingleCourseComponent,
    PasswordChangeUnsuccessfulComponent,
    RegistrationUnsuccessfulComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
