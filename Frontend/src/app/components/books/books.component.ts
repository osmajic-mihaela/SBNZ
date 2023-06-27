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
import { Transaction } from 'src/app/model/Transaction';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent {
  apiHost: string = 'http://localhost:8080/books';
  apiHostOrder: string = 'http://localhost:8080/orders';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  books: Book[] = [];
  basket: OrderItem[] = [];
  loggedUser: User = new User();
  role:string = ''
  isVisible:boolean = false
  creditCard:boolean = false
  transaction:Transaction = new Transaction();


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
        this.getBooks().subscribe(
          {
            next: (response:any) => {
             console.log(response)
             this.books = response
            }
          }
        )

        
    this.getIPAddress()
       }


    });

  }

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.apiHost , {headers: this.headers});
  }

  public createBook(){
    this.router.navigate(['/add-book'])
  }

  public recommendedBooks(){
    this.router.navigate(['/recommended-books'])
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

  shopCard() {
    var orderdto:OrderDTO = new OrderDTO()

    orderdto.transaction = this.transaction
    orderdto.items = this.basket;
    orderdto.userEmail = this.loggedUser.email;

    var oldPrice = 0;
    for (let i = 0; i < this.basket.length; i++) {
      const item = this.basket[i];
      oldPrice = oldPrice + item.orderItemPrice;
    }

    this.createOrderCard(orderdto).subscribe(
      {
        next: (response:Order) => {
         console.log("Racunanje diskaunta:", response)
         this.transaction.amountTrans = response.orderPrice;
         console.log("Transakcija cena:", this.transaction.amountTrans)
         this.createTransaction(this.transaction).subscribe({
          next: (response:any) => {
          
            console.log("kreiranje transakcije",response)
            if(response.validateTransaction){
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
           }
    
    
        });
        }
      }
    )


   


    
  }

  shop() {
    var orderdto:OrderDTO = new OrderDTO()

    orderdto.transaction = this.transaction
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

  public createOrderCard(dto:OrderDTO): Observable<any> {
    return this.http.post<any>(this.apiHostOrder+'/createOrderCard' , dto,{headers: this.headers});
  }

  public createTransaction(transaction:Transaction): Observable<any> {
    return this.http.post<any>('http://localhost:8081/transactions/addTransaction' , transaction,{headers: this.headers});
  }

  public isVisibleShop(){
    this.isVisible = !this.isVisible 
  }

  getIPAddress() {
    this.http.get<{ip: string}>('https://api.ipify.org/?format=json')
      .pipe(
        catchError(error => {
          console.log('Error retrieving IP address:', error);
          return throwError(error);
        })
      )
      .subscribe(data => {
        this.transaction.locationIP = data.ip;
      });
  }

  public ok(){
    if(this.creditCard){
      this.shopCard()
    }else{
      this.shop()
    }
  }

 
}
  


