import { Component, OnInit } from '@angular/core';
import { Transaction } from '../model/transaction.model';
import { TransactionService } from '../services/transaction.service';

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
    // this.transactionService.getClientTransactions().subscribe({
    //   next: (value: Transaction[]) => {
    //     this.transactions = value;
    //   }
    // });
  }

  onApporove(transactionId: number) {
    this.transactionService.approveTransaction(transactionId);
  }

  onCancel(transactionId: number) {
    this.transactionService.cancelTransaction(transactionId);
  }
}
