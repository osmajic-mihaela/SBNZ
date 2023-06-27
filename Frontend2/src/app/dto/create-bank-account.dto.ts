export class CreateBankAccountDto {
    constructor(
        public clientId: string = '',
        public firstName: string = '',
        public lastName: string = '',
        public cardNumber: number = 0,
        public accountNmuber: number = 0,
        public cvv: number = 0,
        public balance: number = 0,
        public expirationDate: Date = new Date()
    ) { }
}