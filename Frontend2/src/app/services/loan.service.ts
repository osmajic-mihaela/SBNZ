import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CreateLoanRequestDto } from "../dto/create-loan-request.dto";
import { LoanRequest } from "../model/loan-request.model";
import { UserService } from "./user.service";

@Injectable({ providedIn: 'root'})

export class LoanService {
    private apiHost = 'http://localhost:8081/loans';
    private headers = { 'content-type': 'application/json' }

    constructor(
        private http: HttpClient,
        private userService: UserService
    ) { }

    getClientLoansRequests() {
        return this.http.get<LoanRequest[]>(`${this.apiHost}`, { headers: this.headers });
    }

    createLoanRequest(dto: CreateLoanRequestDto) {
        return this.http.post<LoanRequest>(`${this.apiHost}`, dto, { headers: this.headers });
    }
}