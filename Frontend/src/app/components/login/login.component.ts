import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDTO } from 'src/app/model/LoginDTO';
import { TokenDTO } from 'src/app/model/token';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {



  constructor(private userService : UserService, private router : Router){}

  loginDto : LoginDTO = new LoginDTO()

  LogIn(){
    this.userService.LogIn(this.loginDto).subscribe(
      {
        next: res =>{
        
          console.log(res)
          this.router.navigate(['/books'])
        },
        error : (err: HttpErrorResponse) => {
          console.log(err.error);
        }
      },
      
    );
  }
}
