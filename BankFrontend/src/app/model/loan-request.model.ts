export class LoanRequest {
    constructor(
        public id: number = 0,
        public clientId: number = 0,
        public loanAmount: number = 0,
        public paymentRate: number = 0,
        public employmentType: string = '',
        public employmentDate: Date = new Date(),
        public approved: boolean = false
    ) { }
}