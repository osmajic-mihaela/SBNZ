import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AuthService } from "./auth.service";
import { CreateLoanRequestDto } from "../dto/create-loan-request.dto";
import { LoanRequest } from "../model/loan-request.model";

@Injectable({ providedIn: 'root'})

export class LoanService {
    private apiHost = 'https//localhost:9000/api/Loans';
    private headers = { 'content-type': 'application/json' }

    constructor(
        private http: HttpClient,
        private authService: AuthService
    ) { }

    getClientLoansRequests() {
        return this.http.get<LoanRequest[]>(`${this.apiHost}`, { headers: this.headers });
    }

    createLoanRequest(dto: CreateLoanRequestDto) {
        return this.http.post<LoanRequest>(`${this.apiHost}`, dto, { headers: this.headers });
    }
}