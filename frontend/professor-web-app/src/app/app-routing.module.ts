import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { LoginAndRegisterGuard } from './guards/login-and-register.guard';
import { ProfessorPagesGuard } from './guards/professor-pages.guard';
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

const routes: Routes = [

    {

      path: '',
      component: HomeComponent,
      canActivate: [ProfessorPagesGuard]


    },
    {

      path: 'login',
      component: LoginComponent,
      canActivate: [LoginAndRegisterGuard],
      canDeactivate: [LoginAndRegisterGuard]

    },
    {

      path: 'register',
      component: RegisterComponent,
      canActivate: [LoginAndRegisterGuard]

    }, 
    {
      path: 'confirmRegistration',
      component: ConfirmRegistrationComponent,
      canActivate: [LoginAndRegisterGuard]

    },
    {
      path: 'registrationUnsuccessful',
      component: RegistrationUnsuccessfulComponent,
      canActivate: [LoginAndRegisterGuard]

    },
    {
      path: 'updateProfile',
      component: UpdateProfileComponent,
      canActivate: [ProfessorPagesGuard]

    },
    {
      path: 'updateEmail',
      component: UpdateEmailComponent,
      canActivate: [ProfessorPagesGuard]
    },
    {
      path: 'updatePassword',
      component: UpdatePasswordComponent,
      canActivate: [ProfessorPagesGuard]
      
    },
    {
      path: 'confirmPasswordChange',
      component: ConfirmUpdatePasswordComponent,
      canActivate: [ProfessorPagesGuard]

    },
    {

      path: 'passwordChangeUnsuccessful',
      component: PasswordChangeUnsuccessfulComponent,
      canActivate: [ProfessorPagesGuard]


    },
    {
      path: 'courses',
      component: CoursesComponent,
      canActivate: [ProfessorPagesGuard]

    },
    {
      path: 'courses/:id',
      component: SingleCourseComponent,
      canActivate: [ProfessorPagesGuard]
    },
    {
      path: 'notifications',
      component: NotificationsComponent,
      canActivate: [ProfessorPagesGuard]

    }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
