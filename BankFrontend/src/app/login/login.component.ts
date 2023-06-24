import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { LoginDto } from '../dto/login.dto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(
    private authService: AuthService
  ) { }

  onLogin(form: NgForm) {
    this.authService.login(
      new LoginDto(
        form.value.email,
        form.value.password
      )
    );
  }

  onCancel(form: NgForm) {
    form.reset();
  }
}
