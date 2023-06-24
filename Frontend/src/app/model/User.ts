export class User {
    name?: string;
    surname?: string;
    email?: string;
    phoneNum?: string;
    password?: string;
    public role?: string;
    isNew?:boolean;
    favoriteGenre?:string[];
    numberOfRatings?:number;
    booksAnRatingsDtos?:any[];
    interestedGenres?:any[];
    interestedWriters?:any[];
    likedBook?:any[];
    shoppedBooks?:any[];

    constructor(){
        this.name = '';
        this.surname = '';
        this.email = '';
        this.phoneNum = '';
        this.password = '';
        this.role = 'USER';
        this.isNew = true;
        this.favoriteGenre=[];
        this.numberOfRatings =0;
        this.booksAnRatingsDtos=[];
        this.interestedGenres=[];
        this.interestedWriters=[];
        this.likedBook=[];
        this.shoppedBooks=[];
    }
}

