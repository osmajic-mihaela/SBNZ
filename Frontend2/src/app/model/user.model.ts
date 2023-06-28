import { BankAccount } from "./bank-account.model";
import { Transaction } from "./transaction.model";

export enum UserRole {
    USER = "USER", 
    ADMIN = "ADMIN"
}

export class User {
    constructor(
        public email: string = '',
        public password: string = '',
        public name: string = '',
        public surname: string = '',
        public phoneNum: string = '',
        public role: UserRole,
        public birthDate: Date = new Date(),
        public monthlyWage: number = 0,
        public employmentType: string = '',
        public employedUntil: Date = new Date(),
        public accountPackages: BankAccount[] = [],
        public transactions: Transaction[] = []
    ) { }
}