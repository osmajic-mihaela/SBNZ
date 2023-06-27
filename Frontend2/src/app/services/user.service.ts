import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { User } from '../model/user.model';
import { LoginDto } from '../dto/login.dto';
import { Router } from '@angular/router';

interface AuthResponseData {
  email: string,
  password: string,
  name: string,
  surname: string,
  phoneNum: string,
  role: string,
  birthDate: Date,
  monthlyWage: number,
  employmentType: string,
  employedUntil: Date
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiHost = 'http://localhost:8081/users/';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  user = new BehaviorSubject<User>(null);

  constructor(
    private http: HttpClient,
    private router: Router
    ) { }

  Register(user : User) : Observable<any>{
    return this.http.post<any>(this.apiHost + 'register', user, this.httpOptions);
  } 

  LogIn(dto : LoginDto) {
    return this.http.post<AuthResponseData>(this.apiHost + 'login', dto, this.httpOptions)
      .pipe(tap(
        resData => {
          const user = new User(
            resData.email,
            resData.password,
            resData.name,
            resData.surname,
            resData.phoneNum,
            resData.role,
            resData.birthDate,
            resData.monthlyWage,
            resData.employmentType,
            resData.employedUntil
          );
          this.user.next(user);
          localStorage.setItem('userData', JSON.stringify(user));
        }
      ))
  } 

  LoggedUser(): Observable<any> {
    return this.http.get<any>(this.apiHost + 'loggedUser',this.httpOptions);
  }

  logout() {
    this.user.next(null);
    this.router.navigate['/login'];
    localStorage.removeItem('userData');
  }
}
