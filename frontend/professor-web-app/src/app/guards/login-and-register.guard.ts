import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanDeactivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginAndRegisterGuard implements CanActivate, CanDeactivate<unknown> {


  constructor(private router: Router) {}


  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      if(localStorage.getItem("token")) {

        let token = <string>localStorage.getItem("token");

        let payload = atob(token.split(".")[1]);

        if(payload.includes("PROFESSOR")) {
          this.router.navigate(["/"]);
          return false;
        }

        localStorage.removeItem("token");
        alert('Please login again with a valid account!');

      }

    return true;
  }
  canDeactivate(
    component: unknown,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {


      if(localStorage.getItem("token")) {

        let token = <string> localStorage.getItem("token");

        let payload = atob(token.split(".")[1]);

        if(!payload.includes("PROFESSOR")) {
          localStorage.removeItem("token");
          alert('Please login again with a valid account!');
          return false;
        }

      }

    return true;
  }
  
}
