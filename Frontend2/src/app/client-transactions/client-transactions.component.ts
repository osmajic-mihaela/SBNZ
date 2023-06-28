import { Component, OnInit } from '@angular/core';
import { Transaction } from '../model/transaction.model';
import { TransactionService } from '../services/transaction.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-client-transactions',
  templateUrl: './client-transactions.component.html',
  styleUrls: ['./client-transactions.component.css']
})
export class ClientTransactionsComponent implements OnInit {

  public transactions: Transaction[] = [];

  constructor(
    private transactionService: TransactionService
  ) { }

  ngOnInit() {
    this.transactionService.getClientTransactions().subscribe({
      next: (value: Transaction[]) => {
        this.transactions = value;
      }
    });
  }

  onApprove(transactionId: string) {
    this.transactionService.approveTransaction(transactionId).subscribe({
      next: (ret: Transaction) => {
        var changed = this.transactions.filter(t => t.id === transactionId)[0];
        this.transactions.splice(this.transactions.indexOf(changed), 1, ret);
        alert('Successfully approved transaction ' + transactionId);
      },
      error: (err: HttpErrorResponse) => {
        alert(err.message);
      }
    });
  }

  onCancel(transactionId: string) {
    this.transactionService.cancelTransaction(transactionId).subscribe({
      next: (ret: Transaction) => {
        var changed = this.transactions.filter(t => t.id === transactionId)[0];
        this.transactions.splice(this.transactions.indexOf(changed), 1, ret);
        alert('Successfully canceled transaction ' + transactionId);
      },
      error: (err: HttpErrorResponse) => {
        alert(err.message);
      }
    });
  }
}
