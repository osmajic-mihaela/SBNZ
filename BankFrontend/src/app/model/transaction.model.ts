export class Transaction {
    constructor(
        public id: number = 0,
        public clientId: number = 0,
        public clientFirstName: string = '',
        public cilentLastName: string = '',
        public clientBankAccount: number = 0,
        public cardNumber: number = 0,
        public expirationDate: Date = new Date(),
        public cvv: number = 0,
        public beneficiarBankAccount: number = 0,
        public amountTrans: number = 0,
        public isSuspicious: boolean = false
    ) { }
}