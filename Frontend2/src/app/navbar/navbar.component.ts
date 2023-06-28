import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { UserService } from '../services/user.service';
import { UserRole } from '../model/user.model';

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
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.userSub = this.userService.user.subscribe(user => {
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
    this.userService.logout();
  }
}
