import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  constructor(private userService : UserService,private router : Router) {}

  user : User = new User();

  confirmPassword : string = '';
  errorMsg : string = '';
  showMsg : boolean = false;

  register(){
    if(this.user.password !== this.confirmPassword){
      this.errorMsg = 'Password and confirm password must be the same!'
      this.showMsg = true;
    }
    else if(this.user.password.length<6){
      this.errorMsg = 'Password need to contain at least 6 characters'
      this.showMsg = true;
    }
    else{
      this.userService.Register(this.user).subscribe({
        next: (response: Response) => {
         alert('Registered successfully!')
         this.router.navigate(['/login'])
        },
        error : (err: HttpErrorResponse) => {
          console.log('username already in use');
        }
    });
    }
  }
}
