import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CreditRequest } from "../model/credit-request.model";
import { UserService } from "./user.service";
import { CreateCreditRequestDto } from "../dto/create-credit-request.dto";

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
}