import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Book } from 'src/app/model/Book';
import { OrderItemDTO } from 'src/app/model/OrderItemDTO';
import { RateDTO } from 'src/app/model/RateDTO';
import { UserService } from 'src/app/services/user.service';
import { Location } from '@angular/common';

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
  rating: number = 0;
  role:string = ''

  constructor(private router: Router,private http: HttpClient,private userService:UserService,private location: Location) { }

  ngOnInit(): void {
    this.userService.LoggedUser().subscribe({
      next: (response:any) => {

        
        if(response.role == 'ADMIN'){
          this.role = 'ADMIN'
        } else if(response.role == 'USER'){
          this.role = 'USER'
        }

       }})
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

  public rateBook(rating:number){
    console.log(rating)
    var temp:RateDTO = new RateDTO();
    temp.book = this.book.name;
    temp.rating = rating;
    console.log(rating)

    this.rateBookRequest(temp).subscribe({
      next: (response: any) => {
        console.log(response)
        alert('Succesfully rate '+this.book.name+' with '+rating)
        window.location.reload();
      },
      error: err => {
        alert('Error')
      }
    });

    
  }

  public rateBookRequest(temp:RateDTO){
    return this.http.post<any>('http://localhost:8080/books/rateBook' , temp,{headers: this.headers});
  }
  
}
