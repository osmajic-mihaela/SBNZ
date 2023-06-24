import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  private userSub: Subscription;
  isAuthenticated: boolean = false;
  role: string = '';
  constructor(
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.userSub = this.authService.user.subscribe(user => {
        this.isAuthenticated = !!user;
        if (!!user) {
          this.role = user.role;
        }
        else {
          this.role = "";
        }
    });
  }

  onLogout(): void {
    this.authService.logout();
  }
}
