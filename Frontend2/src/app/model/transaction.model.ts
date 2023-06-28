export class Transaction {
    constructor(
        public id: string = '',
        public transactionDate: Date = new Date(),
        public senderEmail: string = '',
        public senderFirstName: string = '',
        public senderLastName: string = '',
        public senderAccountId: string = '',
        public senderAccountNumber: number = 0,
        public senderCardNumber: number = 0,
        public beneficiarAccountNumber: number = 0,
        public expirationDate: Date = new Date(),
        public cvv: number = 0,
        public amountTrans: number = 0,
        public locationIP: string = '',
        public isSuspicious: boolean = false,
        public validateTransaction: boolean = false,
        public safeTransaction: boolean = false,
        public isApproved: boolean = false
    ) { }
}