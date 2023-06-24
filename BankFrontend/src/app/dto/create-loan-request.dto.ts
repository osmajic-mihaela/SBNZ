export class CreateLoanRequestDto {
    constructor(
        public clientId: number,
        public loanAmount: number,
        public paymentRate: number,
        public employmentType: string,
        public employmentDate: Date
    ) { }
}