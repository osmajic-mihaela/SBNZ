import { Injectable } from "@angular/core";
import { BehaviorSubject, tap } from "rxjs";
import { User } from "../model/user.model";
import { Router } from "@angular/router";
import { HttpClient } from "@angular/common/http";
import { RegisterDto } from "../dto/register.dto";
import { LoginDto } from "../dto/login.dto";

interface AuthResponseData {
    userId: number,
    firstName: string,
    lastName: string,
    email: string,
    role: string
}

@Injectable({ providedIn: 'root' })

export class AuthService {
    private apiHost = "https://localhost:9000/api/BankUser";
    private headers = {
        'content-type': 'application/json'
    }

    user = new BehaviorSubject<User>(null);

    constructor(
        private router: Router,
        private http: HttpClient
    ) { }

    register(dto: RegisterDto) {
        return this.http.post(`${this.apiHost}/register`, dto, { headers: this.headers });
    }

    login(dto: LoginDto) {
        return this.http.post<AuthResponseData>(`${this.apiHost}/login`, dto, { headers: this.headers })
                        .pipe(tap(
                            resData => {
                                const user = new User(
                                    resData.userId,
                                    resData.firstName, 
                                    resData.lastName,
                                    resData.email,
                                    resData.role
                                );
                                this.user.next(user);
                                localStorage.setItem('userData', JSON.stringify(user));
                            }
                        ));
    }

    logout() {
        this.user.next(null);
        this.router.navigate(['/']);
        localStorage.removeItem('userData');
    }
}