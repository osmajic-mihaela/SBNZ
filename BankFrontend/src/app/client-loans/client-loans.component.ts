import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoanService } from '../service/loan.service';
import { CreateLoanRequestDto } from '../dto/create-loan-request.dto';
import { AuthService } from '../service/auth.service';
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
    private authService: AuthService
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
        this.authService.user.value.userId,
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
