import { Book } from "./Book";

export class OrderItemDTO {
   
    bookName?: string;
    book?: Book;
    


    constructor(){
        this.bookName = '';
        this.book = new Book();
        
    }
}