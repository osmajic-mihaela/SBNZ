export class CreateBankAccountDto {
    constructor(
        public clientId: number,
        public accountNumber: number,
        public cardNumber: number,
        public cvv: number,
        public balance: number
    ) { }
}