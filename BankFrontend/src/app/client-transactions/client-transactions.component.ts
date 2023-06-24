import { Component, OnInit } from '@angular/core';
import { Transaction } from '../model/transaction.model';
import { TransactionService } from '../service/transaction.service';

@Component({
  selector: 'app-client-transactions',
  templateUrl: './client-transactions.component.html',
  styleUrls: ['./client-transactions.component.css']
})
export class ClientTransactionsComponent implements OnInit {

  public transactions: Transaction[] = [new Transaction(1, 2, 'Pera', 'Peric', 1234, 5678, new Date(), 654, 123456, 1000, true)];

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
