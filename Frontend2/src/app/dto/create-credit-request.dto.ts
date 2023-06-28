import { EmployType } from "../model/credit-request.model";

export class CreateCreditRequestDto {
    constructor(
        public email: string = '',
        public amount: number = 0,
        public rateNumber: number = 0,
        public mounthlyRate: number = 0,
        public employType: EmployType,
        public ages: number = 0,
        public startContract: Date = new Date(),
        public endContract: Date = new Date()
    ) { }
}