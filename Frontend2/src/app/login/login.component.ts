import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDto } from 'src/app/dto/login.dto';
import { UserService } from 'src/app/services/user.service';

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

  loginDto : LoginDto = new LoginDto();

  LogIn(){
    this.userService.LogIn(this.loginDto).subscribe(
      {
        next: res =>{
        
          console.log(res)

        },
        error : (err: HttpErrorResponse) => {
          console.log(err.error);
        }
      },
      
    );
  }
}
