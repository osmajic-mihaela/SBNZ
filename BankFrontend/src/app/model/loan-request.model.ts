export class LoanRequest {
    constructor(
        public id: number,
        public clientId: number,
        public loanAmount: number,
        public paymentRate: number,
        public employmentType: string,
        public employmentDate: Date
    ) { }
}