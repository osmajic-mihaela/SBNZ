export class User {
    name?: string;
    surname?: string;
    email?: string;
    phoneNum?: string;
    password?: string;
    public role?: string;


    constructor(){
        this.name = '';
        this.surname = '';
        this.email = '';
        this.phoneNum = '';
        this.password = '';
        this.role = 'USER';

    }
}

