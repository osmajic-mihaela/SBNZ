import { OrderItem } from "./OrderItem";
import { Transaction } from "./Transaction";

export class OrderDTO {
    userEmail?: string;
    items?: OrderItem[];
    transaction?:Transaction;

    constructor(){
        this.userEmail = '';
        this.items = [];
        this.transaction = new Transaction();
    }
}