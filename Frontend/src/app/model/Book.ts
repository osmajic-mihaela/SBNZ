export class Book {
    name?: string;
    writer?: string;
    price?: number;
    category?: string;
    rating?: number;
    dtos?: any[];

    constructor(){
        this.name = '';
        this.writer = '';
        this.price = 0;
        this.category = '';
        this.rating = 0;
        this.dtos =[];
    }
}


