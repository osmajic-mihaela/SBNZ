import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Book } from 'src/app/model/Book';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/model/User';
import { OrderItem } from 'src/app/model/OrderItem';
import { OrderDTO } from 'src/app/model/OrderDTO';
import { Order } from 'src/app/model/Order';

@Component({
  selector: 'app-popular-books',
  templateUrl: './popular-books.component.html',
  styleUrls: ['./popular-books.component.css']
})
export class PopularBooksComponent {
  apiHost: string = 'http://localhost:8080/books';
  apiHostOrder: string = 'http://localhost:8080/orders';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  books: Book[] = [];
  basket: OrderItem[] = [];
  loggedUser: User = new User();
  role:string = ''

  constructor(private router : Router,private http: HttpClient,private userService:UserService) {}

  ngOnInit(): void {
    this.userService.LoggedUser().subscribe({
      next: (response:any) => {
        console.log('useerr',response)
        this.loggedUser = response
        
        if(response.role == 'ADMIN'){
          this.role = 'ADMIN'
        } else if(response.role == 'USER'){
          this.role = 'USER'
        }
        console.log(this.role)

        this.getPopularBooks().subscribe(
          {
            next: (response:any) => {
             console.log(response)
             this.books = response
            }
          }
        )
       }


    });
   
  }

  getPopularBooks(): Observable<Book[]> {
    if (this.role == "USER")
    return this.http.get<Book[]>(this.apiHost+"/unregisteredPopularBooks" , {headers: this.headers});
    else
      return this.http.get<Book[]>(this.apiHost+"/unregisteredPopularBooks" , {headers: this.headers});
  }

  public createBook(){
    this.router.navigate(['/add-book'])
  }

    handleArray(array: any) {
    console.log('array',array); 
    var temp: OrderItem[] =[];
    if(this.basket.length > 0){
      var isThere = false;
      for (let i = 0; i < this.basket.length; i++) {
        const item = this.basket[i];
        if(array.bookName == item.bookName){
            isThere = true
        }
      }

      if(isThere == true){
        for (let i = 0; i < this.basket.length; i++) {
          const item = this.basket[i];
          if(array.bookName != item.bookName){
              temp.push(item)
          }else{
            item.quantity =item.quantity+ 1;
            temp.push(item)
           
          }
    
        }
        this.basket = temp;
      }else{
        this.basket.push(array)
      }

      

      
    }else{
      console.log('up')
      this.basket.push(array)
    }

    

    
    console.log('basket',this.basket)
  }

  shop() {
    var orderdto:OrderDTO = new OrderDTO()
    orderdto.items = this.basket;
    orderdto.userEmail = this.loggedUser.email;

    var oldPrice = 0;
    for (let i = 0; i < this.basket.length; i++) {
      const item = this.basket[i];
      oldPrice = oldPrice + item.orderItemPrice;
    }

    this.createOrder(orderdto).subscribe(
      {
        next: (response:Order) => {
         console.log(response)
         alert("Old price: "+oldPrice+" Price with discount:"+response.orderPrice)
         this.basket = []
        }
      }
    )
  }

  public createOrder(dto:OrderDTO): Observable<any> {
    return this.http.post<any>(this.apiHostOrder+'/createOrder' , dto,{headers: this.headers});
  }

}
