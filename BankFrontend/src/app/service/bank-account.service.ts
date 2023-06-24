import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AuthService } from "./auth.service";
import { BankAccount } from "../model/bank-account.model";
import { CreateBankAccountDto } from "../dto/create-bank-account.dto";

@Injectable({ providedIn: 'root' })

export class BankAccountService {

    private apiHost = 'https://localhost:9000/api/bankAccounts';
    private headers = {
        'content-type': 'application/json'
    };

    constructor(
        private http: HttpClient,
        private authService: AuthService
    ) { }

    getClientAccounts() {
        let params = new HttpParams();
        params = params.set('id', this.authService.user.value.userId);

        return this.http.get<BankAccount[]>(`${this.apiHost}`, { headers: this.headers, params: params });
    }

    createAccount(dto: CreateBankAccountDto) {
        // this.http.post(`${this.apiHost} + /create`, dto, { headers: this.headers }).subscribe({
        //     next: () => {
        //         alert('Successfully created the account!');
        //     },
        //     error: () => {
        //         alert('There was an error with creating your account!');
        //     }
        // })
    }
}