import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginDto } from 'src/app/dto/login.dto';
import { UserService } from 'src/app/services/user.service';
import { User, UserRole } from '../model/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(
    private userService : UserService, 
    private router : Router
    ) { }

  onLogin(form: NgForm) {
    this.userService.LogIn(
      new LoginDto(
        form.value.email,
        form.value.password
      )
    ).subscribe({
      next: (user: User) => {
        console.log(user);
        if (user.role == UserRole.ADMIN) {
          this.router.navigate(['/loan-approval']);
        }
        else if (user.role == UserRole.USER) {
          this.router.navigate(['/bank-accounts']);
        }
      },
      error: (err: HttpErrorResponse) => {
        console.log(err.error);
      }
    })
    form.reset();
  }

  onCancel(form: NgForm) {
    form.reset();
  }
}
