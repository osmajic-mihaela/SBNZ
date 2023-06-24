import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable, map, take } from "rxjs";
import { AuthService } from "./auth.service";

@Injectable({ providedIn: 'root' })

export class AuthGuard implements CanActivate {

    constructor(
        private authService: AuthService,
        private router: Router
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
        if (!!this.authService.user) {

            const userRole = this.authService.user.value.role;

            if (route.data.role && route.data.role.indexOf(userRole) === -1) {
                this.router.navigate(['/']);
                return false;
            }
            return true;
            
        }
        
        this.router.navigate(['/']);
        return false;
    }
}