export class BankAccount {
    constructor(
        public id: number,
        public clientId: number,
        public accountNumber: number,
        public cardNumber: number,
        public validFrom: Date,
        public validTo: Date,
        public cvv: number,
        public status: string,
        public balance: number
    ) { }
}