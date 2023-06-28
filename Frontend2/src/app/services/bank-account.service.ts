import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserService } from "./user.service";
import { BankAccount } from "../model/bank-account.model";
import { CreateBankAccountDto } from "../dto/create-bank-account.dto";

@Injectable({ providedIn: 'root' })

export class BankAccountService {

    private apiHost = 'http://localhost:8081/accountPackages';
    private headers = {
        'content-type': 'application/json'
    };

    constructor(
        private http: HttpClient,
        private userService: UserService
    ) { }

    getClientAccounts() {
        let params = new HttpParams();
        params = params.set('id', this.userService.user.value.email);

        return this.http.get<BankAccount[]>(`${this.apiHost}`, { headers: this.headers, params: params });
    }

    createAccount(dto: CreateBankAccountDto) {
        return this.http.post(`${this.apiHost}/addPackage`, dto, { headers: this.headers });
    }
}