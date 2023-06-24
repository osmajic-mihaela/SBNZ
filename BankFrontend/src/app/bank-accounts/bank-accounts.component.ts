import { Component, OnInit } from '@angular/core';
import { BankAccountService } from '../service/bank-account.service';
import { BankAccount } from '../model/bank-account.model';
import { NgForm } from '@angular/forms';
import { CreateBankAccountDto } from '../dto/create-bank-account.dto';
import { AuthService } from '../service/auth.service';

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
    private authService: AuthService
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
        this.authService.user.value.userId,
        form.value.accountNumber,
        form.value.cardNumber,
        form.value.cvv,
        form.value.balance
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