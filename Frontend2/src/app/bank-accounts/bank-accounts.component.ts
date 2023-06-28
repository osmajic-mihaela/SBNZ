import { Component, OnInit } from '@angular/core';
import { BankAccount } from '../model/bank-account.model';
import { NgForm } from '@angular/forms';
import { CreateBankAccountDto } from '../dto/create-bank-account.dto';
import { BankAccountService } from '../services/bank-account.service';
import { UserService } from '../services/user.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-bank-accounts',
  templateUrl: './bank-accounts.component.html',
  styleUrls: ['./bank-accounts.component.css']
})
export class BankAccountsComponent implements OnInit {

  public bankAccounts: BankAccount[] = [];
  public showAddModal: boolean = false;

  constructor(
    private bankAccountService: BankAccountService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.bankAccountService.getClientAccounts().subscribe(
      (value) => {
        this.bankAccounts = value;
      }
    );
  }

  onAdd(form: NgForm) {
    this.bankAccountService.createAccount(
      new CreateBankAccountDto(
        this.userService.user.value.email,
        this.userService.user.value.name,
        this.userService.user.value.surname,
        form.value.cardNumber,
        form.value.accountNumber,
        form.value.cvv,
        form.value.balance,
        form.value.expirationDate
      )
    ).subscribe({
      next: (added: BankAccount) => {
        this.bankAccounts.push(added);
        alert('Successfully added a new packet!');
      },
      error: (err: HttpErrorResponse) => {
        alert("There was an error with creating the account !");
      }
    })
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