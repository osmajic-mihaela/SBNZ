export class CreateLoanRequestDto {
    constructor(
        public clientId: string,
        public loanAmount: number,
        public paymentRate: number,
        public employmentType: string,
        public employmentDate: Date
    ) { }
}