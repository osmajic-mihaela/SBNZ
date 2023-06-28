import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Transaction } from "../model/transaction.model";
import { Observable } from "rxjs";
import { UserService } from "./user.service";

@Injectable({ providedIn: 'root'})

export class TransactionService {

    private apiHost = 'http://localhost:8081/transactions';
    private headers = { 'content-type': 'application/json' }

    constructor(
        private http: HttpClient,
        private userService: UserService
    ) { }

    getClientTransactions(): Observable<Transaction[]>  {
        let params = new HttpParams();
        params = params.set("email", this.userService.user.value.email);
        return this.http.get<Transaction[]>(`${this.apiHost}`, { headers: this.headers, params: params });
    }

    approveTransaction(transactionId: string): Observable<Transaction> {
        let params = new HttpParams();
        params = params.set('id', transactionId);
        console.log(transactionId);
        return this.http.put<Transaction>(`${this.apiHost}/approveTransaction/` + transactionId, { headers: this.headers });
    }
    
    cancelTransaction(transactionId: string): Observable<Transaction> {
        let params = new HttpParams();
        params = params.set('id', transactionId);
        console.log(transactionId);
        return this.http.put<Transaction>(`${this.apiHost}/cancelTransaction/` + transactionId, { headers: this.headers });
    }
}