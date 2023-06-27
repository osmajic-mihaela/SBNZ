import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoanService } from '../services/loan.service';
import { CreateLoanRequestDto } from '../dto/create-loan-request.dto';
import { UserService } from '../services/user.service';
import { LoanRequest } from '../model/loan-request.model';

@Component({
  selector: 'app-client-loans',
  templateUrl: './client-loans.component.html',
  styleUrls: ['./client-loans.component.css']
})
export class ClientLoansComponent implements OnInit {

  public employmentType: string = '';
  public loanRequests: LoanRequest[] = [];

  constructor(
    private loanService: LoanService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.loanService.getClientLoansRequests().subscribe(
      (value) => {
        this.loanRequests = value;
      }
    )
  }

  onAdd(form: NgForm) {
    this.loanService.createLoanRequest(
      new CreateLoanRequestDto(
        this.userService.user.value.email,
        form.value.loanAmount,
        form.value.paymentRate,
        form.value.employmentType,
        form.value.employmentDate
      )
    );
    this.closeModal(form);
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
    form.reset();
  }
}
