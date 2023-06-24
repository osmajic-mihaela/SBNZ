import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { RegisterDto } from '../dto/register.dto';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  constructor(
    private authService: AuthService
  ) { }

  onRegister(form: NgForm) {
    this.authService.register(
      new RegisterDto(
        form.value.firstName,
        form.value.lastName,
        form.value.email,
        form.value.password
      )
    );
  }

  onCancel(form: NgForm) {
    form.reset();
  }
}
