import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CreditRequest } from "../model/credit-request.model";
import { UserService } from "./user.service";
import { Observable } from "rxjs";

@Injectable({ providedIn: 'root'})

export class LoanService {
    private apiHost = 'http://localhost:8081/credits';
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
        return this.http.post<CreditRequest>(`${this.apiHost}/addRequest`, dto, { headers: this.headers });
    }

    approveCredit(id: string): Observable<CreditRequest> {
        return this.http.put<CreditRequest>(`${this.apiHost}/acceptRequest/` + id, { headers: this.headers });
    }

    cancelCredit(id: string ): Observable<CreditRequest> {
        return this.http.put<CreditRequest>(`${this.apiHost}/rejectRequest/` + id, { headers: this.headers });
    }
}