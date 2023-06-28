import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CreditRequest } from "../model/credit-request.model";
import { UserService } from "./user.service";
import { Observable } from "rxjs";

@Injectable({ providedIn: 'root'})

export class LoanService {
    private apiHost = 'http://localhost:8081/credit-requests';
    private headers = { 'content-type': 'application/json' }

    constructor(
        private http: HttpClient,
        private userService: UserService
    ) { }

    getClientCreditRequests() {
        return this.http.get<CreditRequest[]>(`${this.apiHost}`, { headers: this.headers });
    }

    createCreditRequest(dto: CreditRequest) {
        console.log(dto);
        return this.http.post<CreditRequest>(`${this.apiHost}`, dto, { headers: this.headers });
    }

    approveCredit(id: string): Observable<CreditRequest> {
        let params = new HttpParams();
        params = params.set('id', id);
        return this.http.put<CreditRequest>(`${this.apiHost}/approve`, { headers: this.headers, params: params });
    }

    cancelCredit(id: string ): Observable<CreditRequest> {
        let params = new HttpParams();
        params = params.set('id', id);
        return this.http.put<CreditRequest>(`${this.apiHost}/cancel`, { headers: this.headers, params: params });
    }
}