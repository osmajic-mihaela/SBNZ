import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Frontend';
  apiHost: string = 'http://localhost:8080/users';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private router : Router,private http: HttpClient) {}
  register(){
   
    this.router.navigate(['register'])
  }

  logout(){
    this.logoutt().subscribe(
      {
        next: (response:any) => {

        }
      }
    )
    this.router.navigate(['login'])
  }
  

  books(){
    this.router.navigate(['books'])
  }

  public logoutt(): Observable<any> {
    return this.http.get<any>(this.apiHost+'/logout' ,{headers: this.headers});
  }
  
  
}
