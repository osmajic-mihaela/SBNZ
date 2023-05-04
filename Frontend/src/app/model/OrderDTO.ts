import { OrderItem } from "./OrderItem";

export class OrderDTO {
    userEmail?: string;
    items?: OrderItem[];

    constructor(){
        this.userEmail = '';
        this.items = [];
    }
}