import { Book } from "./Book";

export class OrderItem {
    orderId?: string;
    bookName?: string;
    quantity: number;
    book?: Book;
    orderItemPrice:number;
    discount?:number;


    constructor(){
        this.orderId = '';
        this.bookName = '';
        this.quantity = 0;
        this.book = new Book();
        this.orderItemPrice =0;
        this.discount =0;
    }
}