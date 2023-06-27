export class Transaction {
    constructor(
        public id: string = '',
        public transactionDate: Date = new Date(),
        public senderId: string = '',
        public senderFirstName: string = '',
        public senderLastName: string = '',
        public senderBankAccountId: string = '',
        public senderAccountNumber: number = 0,
        public senderCardNumber: number = 0,
        public expirationDate: Date = new Date(),
        public cvv: number = 0,
        public amountTrans: number = 0,
        public location: string = '',
        public isSuspicious: boolean = false
    ) { }
}