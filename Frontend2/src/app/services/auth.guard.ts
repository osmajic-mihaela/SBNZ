import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { UserService } from './user.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    constructor(
      private router:Router, 
      private userService: UserService
      ) { }
  
    canActivate(
      route: ActivatedRouteSnapshot, 
      state: RouterStateSnapshot
  ): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
      return this.checkLoggedUserRole(route);
      // return this.authService.user.pipe(
      //     take(1),
      //     map( user => {
      //         const isAuth = !!user;
      //         if (isAuth) {
      //             return true;
      //         }
      //         return this.router.createUrlTree(['/login']);
      //     }));
  }

  private checkLoggedUserRole(route: ActivatedRouteSnapshot): boolean {
      if (!!this.userService.user) {

        const userRole = this.userService.user.value.role;

        if (route.data.role && route.data.role.indexOf(userRole) === -1) {
            this.router.navigate(['/login']);
            return false;
        }
        return true;
          
      }
      
      this.router.navigate(['/login']);
      return false;
  }
}