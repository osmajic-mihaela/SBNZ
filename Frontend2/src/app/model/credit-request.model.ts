export enum EmployType {
    EMPLOYED = 'EMPLOYED',
    UNEMPLOYED = 'UNEMPLOYED',
    EMPLOYED_PERIOD = 'EMPLOYED_PERIOD'
}

export enum CreditRequestType {
    PENDING = 'PENDING',
    ACCEPT = 'ACCEPT',
    REJECT = 'REJECT'
}

export class CreditRequest {
    constructor(
        public id: string = '',
        public email: string = '',
        public amount: number = 0,
        public rateNumber: number = 0,
        public mounthlyRate: number = 0,
        public employType: EmployType,
        public ages: number = 0,
        public startContract: Date,
        public endContract: Date,
        public minDate: Date,
        public maxDate: Date,
        public avgIncome: number = 0,
        public avgCosts: number = 0,
        public isMissedLoan: boolean = false,
        public creditRequestType: CreditRequestType,
        public recommend: boolean = false
    ) { }
}