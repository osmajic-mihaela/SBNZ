export class CreateBankAccountDto {
    constructor(
        public clientId: string = '',
        public clientFirstName: string = '',
        public clientLastName: string = '',
        public cardNumber: number = 0,
        public accountNumber: number = 0,
        public cvv: number = 0,
        public balance: number = 0,
        public expirationDate: Date = new Date()
    ) { }
}