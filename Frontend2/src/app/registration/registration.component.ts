import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User, UserRole } from 'src/app/model/user.model';
import { UserService } from 'src/app/services/user.service';
import {FormControl, FormsModule, NgForm, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  confirmPassword : string = '';
  errorMsg : string = '';
  showMsg : boolean = false;
  employmentType: string = 'UNEMPLOYED';

  userRoles = Object.values(UserRole);

  constructor(
    private userService : UserService,
    private router : Router
    ) { }


  onRegister(form: NgForm) {
    if ( form.value.password != form.value.confirmPassword ) {
      this.errorMsg = 'Password and confirm password must be the same!';
      this.showMsg = true;
    }
    else if ( form.value.password.length < 6 ) {
      this.errorMsg = 'Password need to contain at least 6 characters';
      this.showMsg = true;
    }
    else {
      this.userService.Register(
        new User(
          form.value.email,
          form.value.password,
          form.value.name,
          form.value.surname,
          form.value.phoneNumber,
          form.value.role,
          form.value.birthDate,
          form.value.monthlyWage,
          form.value.employmentType,
          form.value.employedUntil
        )
      ).subscribe({
        next: (response: Response) => {
          alert('User successfully registered!');
          this.router.navigate(['/login']);
        },
        error: (err: HttpErrorResponse) => {
          alert('Email already in use!');
        }
      })
    }
    form.reset();
  }

  onCancel(form: NgForm) {
    form.reset();
  }
  // register(){
  

  //   if(form.value.password !== this.confirmPassword){
  //     this.errorMsg = 'Password and confirm password must be the same!'
  //     this.showMsg = true;
  //   }
  //   else if(this.user.password.length<6){
  //     this.errorMsg = 'Password need to contain at least 6 characters'
  //     this.showMsg = true;
  //   }
  //   else{
  //     this.userService.Register(this.user).subscribe({
  //       next: (response: Response) => {
  //        alert('Registered successfully!')
  //        this.router.navigate(['/login'])
  //       },
  //       error : (err: HttpErrorResponse) => {
  //         console.log('username already in use');
  //       }
  //   });
  //   }
  // }
}
