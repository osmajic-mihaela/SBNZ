import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoanService } from '../services/loan.service';
import { CreateLoanRequestDto } from '../dto/create-loan-request.dto';
import { UserService } from '../services/user.service';
import { CreditRequest, EmployType } from '../model/credit-request.model';
import { CreateCreditRequestDto } from '../dto/create-credit-request.dto';

@Component({
  selector: 'app-client-loans',
  templateUrl: './client-loans.component.html',
  styleUrls: ['./client-loans.component.css']
})
export class ClientLoansComponent implements OnInit {

  public employmentType: EmployType;
  public creditRequests: CreditRequest[] = [];
  public rateCalculate: number = 0;
  public amount: number = 0;
  public rateNumber: number = 0;
  public age: number = 0;
  employTypes = Object.values(EmployType);
  public dateNow: Date = new Date();
  public dateOfBirth: Date;
  public endContract: Date;
  constructor(
    private loanService: LoanService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.loanService.getClientCreditRequests().subscribe(
      (value) => {
        this.creditRequests = value;
      }
    )
    // kobasica ozbiljna 
    this.dateOfBirth = new Date(Date.parse(this.userService.user.value.birthDate.toString()));
    this.endContract = new Date(Date.parse(this.userService.user.value.employedUntil.toString()));
    this.age = this.dateNow.getFullYear() - this.dateOfBirth.getFullYear();

    if (this.userService.user.value.employmentType == 'UNEMPLOYED') {
      this.employmentType = EmployType.UNEMPLOYED;
    }
    else if (this.userService.user.value.employmentType == 'EMPLOYED') {
      this.employmentType = EmployType.EMPLOYED;
    }
    else {
      this.employmentType = EmployType.EMPLOYED_PERIOD;
    }
  }

  onAdd(form: NgForm) {
    console.log(form.value);
    this.loanService.createCreditRequest(
      new CreditRequest(
        null,
        this.userService.user.value.email,
        form.value.amount,
        form.value.rateNumber,
        this.rateCalculate,
        this.employmentType,
        this.age,
        new Date(Date.parse(form.value.startContract)),
        form.value.endContract ? new Date(Date.parse(form.value.endContract)) : null,
        null,
        null,
        null,
        null,
        null,
        null,
        false
      )
    );
    this.closeModal(form);
  }

  updateMonthlyRate(): void {
    if ( this.amount > 0 && this.rateNumber > 0) {
      this.rateCalculate = this.amount / this.rateNumber;
    }
  }

  openModal(): void {
    const modal = document.getElementById('addModal');
    if (modal != null) {
      modal.style.display = 'block';
    }
  }

  closeModal(form: NgForm): void {
    const modal = document.getElementById('addModal');
    if (modal != null) {
      modal.style.display = 'none';
    }
  }
}
