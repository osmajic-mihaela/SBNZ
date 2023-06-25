import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';
import { LoginDTO } from '../model/LoginDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl = 'http://localhost:8081/users/';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  Register(user : User) : Observable<any>{
    console.log(user)
    return this.http.post<any>(this.usersUrl + 'register',user,this.httpOptions);
  } 

  LogIn(loginDto : LoginDTO): Observable<any> {
    return this.http.post<any>(this.usersUrl + 'login',loginDto,this.httpOptions);
  } 

  LoggedUser(): Observable<any> {
    return this.http.get<any>(this.usersUrl + 'loggedUser',this.httpOptions);
  } 
}
