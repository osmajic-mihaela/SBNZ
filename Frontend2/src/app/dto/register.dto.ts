export class RegisterDto {
    constructor(
        public email: string = '',
        public password: string = '',
        public name: string = '',
        public surname: string = '',
        public phoneNum: string = '',
        public role: string = '',
        public birthDate: Date = new Date(),
        public monthlyWage: number = 0,
        public employmentType: string = '',
        public employedUntil: Date = new Date()
    ) { }
}