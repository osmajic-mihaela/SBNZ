import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AuthService } from "./auth.service";
import { Transaction } from "../model/transaction.model";
import { Observable } from "rxjs";

@Injectable({ providedIn: 'root'})

export class TransactionService {

    private apiHost = 'localhost:9000/api/Transactions';
    private headers = { 'content-type': 'application/json' }

    constructor(
        private http: HttpClient,
        private authService: AuthService
    ) { }

    getClientTransactions(): Observable<Transaction[]>  {
        return this.http.get<Transaction[]>(`${this.apiHost}`, { headers: this.headers });
    }

    approveTransaction(transactionId: number): void {
        this.http.put(`${this.apiHost}/Approve/` + transactionId, { headers: this.headers }).subscribe({
            next: () => {
                alert('Successfully approved transaction ' + transactionId + '!');
            },
            error: () => {
                alert('There was an error with approving the transaction ' + transactionId + "...");
            }
        })
    }
    
    cancelTransaction(transactionId: number): void {
        this.http.put(`${this.apiHost}/Cancel/` + transactionId, { headers: this.headers }).subscribe({
            next: () => {
                alert('Successfully canceled transaction ' + transactionId + '!');
            },
            error: () => {
                alert('There was an error with canceling the transaction ' + transactionId + "...");
            }
        })
    }
}