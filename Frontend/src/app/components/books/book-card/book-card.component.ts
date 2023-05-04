import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Book } from 'src/app/model/Book';
import { OrderItemDTO } from 'src/app/model/OrderItemDTO';

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.css']
})
export class BookCardComponent {
  apiHost: string = 'http://localhost:8080/orderItems';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  @Input() book = new Book();
  //@Input() basket:Book[] = [];
  dto:OrderItemDTO = new OrderItemDTO();
  @Output() arrayEmitted  = new EventEmitter<Book>();


  constructor(private router: Router,private http: HttpClient) { }

  ngOnInit(): void {
  }
  centerProfile(){
    //this.router.navigate(['center-profile'], { state: { centerId: this.center.id } })
  }

  emitArray() {
    this.dto = new OrderItemDTO();
    this.dto.book = this.book;
    this.dto.bookName = this.book.name;

    this.createOrderItem(this.dto).subscribe({
      next: (response: any) => {
        this.arrayEmitted.emit(response);
        
      },
      error: err => {
        alert('Book exist')
      }
  });
    
  }

  public createOrderItem(dto:OrderItemDTO): Observable<any> {
    return this.http.post<any>(this.apiHost+'/createOrderItem' , dto,{headers: this.headers});
  }

  
  
}
