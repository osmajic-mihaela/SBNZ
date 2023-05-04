import { Book } from "./Book";
import { OrderItem } from "./OrderItem";

export class Order {
    orderId?: string;
    itemsIDs:string[];
    items:OrderItem[];
    orderPrice?:number;
    userEmail?:string;
    orderDate?:Date;
    discount?:number;


    constructor(){
        this.orderId = '';
        this.discount =0;
        this.itemsIDs = [];
        this.items = [];
        this.orderPrice = 0;
        this.userEmail = '';
        this.orderDate = new Date();
        this.discount =0;

    }
}