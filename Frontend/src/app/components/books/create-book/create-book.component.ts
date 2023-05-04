import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Book } from 'src/app/model/Book';

@Component({
  selector: 'app-create-book',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent {
  apiHost: string = 'http://localhost:8080/books';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private router : Router,private http: HttpClient) {}

  book : Book = new Book();

  confirmPassword : string = '';
  errorMsg : string = '';
  showMsg : boolean = false;

  addBook(){
    if(this.book.name ==''){
      this.errorMsg = 'Password and confirm password must be the same!'
      this.showMsg = true;
    }
    else if(this.book.writer ==''){
      this.errorMsg = 'Password need to contain at least 6 characters'
      this.showMsg = true;
    }

    else{
      this.createBook().subscribe({
        next: (response: Response) => {
         alert('Add successfully!')
         this.router.navigate(['/books'])
        },
        error: err => {
          alert('Book exist')
        }
    });
    }
  }

  public createBook(): Observable<any> {
    return this.http.post<any>(this.apiHost+'/createBook' ,this.book ,{headers: this.headers});
  }

}
