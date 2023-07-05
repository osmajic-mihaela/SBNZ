import { Component } from '@angular/core';
import { CreditRequest, CreditRequestType, EmployType } from '../model/credit-request.model';
import { LoanService } from '../services/loan.service';
import { UserService } from '../services/user.service';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-credit-approval',
  templateUrl: './credit-approval.component.html',
  styleUrls: ['./credit-approval.component.css']
})
export class CreditApprovalComponent {

  public employTypes = Object.values(EmployType);
  public employmentType: EmployType;
  public amount: number = 0;
  public rateNumber: number = 0;
  public rateCalculate: number = 0;

  public creditRequests: CreditRequest[] = [];

  constructor(
    private loanService: LoanService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.loanService.getClientCreditRequests().subscribe({
      next: (ret: CreditRequest[]) => {
        this.creditRequests = ret;
        console.log(ret);
        console.log(ret[0].creditRequestType == 'PENDING')
      },
      error: (err: HttpErrorResponse) => {
        alert(err.message);
      }
    })
  }
  onApprove(id: string): any {
    this.loanService.approveCredit(id).subscribe({
      next: (ret: CreditRequest) => {
        var changed = this.creditRequests.filter(t => t.id === id)[0];
        this.creditRequests.splice(this.creditRequests.indexOf(changed), 1, ret);
        alert('Successfully approved credit request ' + id);
        this.loanService.getClientCreditRequests().subscribe({
          next: (ret: CreditRequest[]) => {
            this.creditRequests = ret;
            console.log(ret);
            console.log(ret[0].creditRequestType == 'PENDING')
          },
          error: (err: HttpErrorResponse) => {
            alert(err.message);
          }
        })
      },
      error: (err: HttpErrorResponse) => {
        alert(err.message);
      }
    });
  }

  onCancel(id: string): any {
    this.loanService.cancelCredit(id).subscribe({
      next: (ret: CreditRequest) => {
        var changed = this.creditRequests.filter(t => t.id === id)[0];
        this.creditRequests.splice(this.creditRequests.indexOf(changed), 1, ret);
        alert('Successfully canceled credit request ' + id);
        this.loanService.getClientCreditRequests().subscribe({
          next: (ret: CreditRequest[]) => {
            this.creditRequests = ret;
            console.log(ret);
            console.log(ret[0].creditRequestType == 'PENDING')
          },
          error: (err: HttpErrorResponse) => {
            alert(err.message);
          }
        })
      },
      error: (err: HttpErrorResponse) => {
        alert(err.message);
      }
    });
  }

  onAdd(form: NgForm): void {
    if(form.value.employmentType==EmployType.UNEMPLOYED){
      this.loanService.createCreditRequest(
        new CreditRequest(
          null,
          form.value.clientEmail,
          form.value.amount,
          form.value.rateNumber,
          this.rateCalculate,
          form.value.employmentType,
          form.value.age,
          new Date(Date.parse(form.value.minDate)),
          new Date(Date.parse(form.value.maxDate)),
          new Date(Date.parse(form.value.minDate)),
          new Date(Date.parse(form.value.maxDate)),
          0,
          0,
          false,
          CreditRequestType.PENDING,
          false
        )
      ).subscribe({
        next: (ret: CreditRequest) => {
          this.creditRequests.push(ret);
          alert('Successfully created the credit request ' + ret.id);
        },
        error: (err: HttpErrorResponse) => {
          alert(err.message);
        }
      });
    }else{
      this.loanService.createCreditRequest(
        new CreditRequest(
          null,
          form.value.clientEmail,
          form.value.amount,
          form.value.rateNumber,
          this.rateCalculate,
          form.value.employmentType,
          form.value.age,
          form.value.startContract ? new Date(Date.parse(form.value.startContract)) : null,
          form.value.endContract ? new Date(Date.parse(form.value.endContract)) : new Date(2099, 0, 1),
          new Date(Date.parse(form.value.minDate)),
          new Date(Date.parse(form.value.maxDate)),
          0,
          0,
          false,
          CreditRequestType.PENDING,
          false
        )
      ).subscribe({
        next: (ret: CreditRequest) => {
          this.creditRequests.push(ret);
          alert('Successfully created the credit request ' + ret.id);
        },
        error: (err: HttpErrorResponse) => {
          alert(err.message);
        }
      });
    }

    
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
    form.reset();
  }
}
