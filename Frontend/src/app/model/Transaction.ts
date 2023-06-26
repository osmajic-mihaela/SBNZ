import { Book } from "./Book";
import { OrderItem } from "./OrderItem";

export class Transaction {
    id: string;
    transactionDate: Date;
    senderEmail: string;
    senderFirstName: string;
    senderLastName: string;
    senderAccountId: string;
    senderAccountNumber: number;
    senderCardNumber: number;
    beneficiarAccountNumber: number;
    cardExpirationDate: Date;
    cvv: number;
    amountTrans: number;
    locationIP: string;
    isSuspicious: boolean;
  
    constructor() {
      this.id = '';
      this.transactionDate = new Date();
      this.senderEmail = '';
      this.senderFirstName = '';
      this.senderLastName = '';
      this.senderAccountId = '';
      this.senderAccountNumber = 0;
      this.senderCardNumber = 0;
      this.beneficiarAccountNumber = 0;
      this.cardExpirationDate = new Date();
      this.cvv = 0;
      this.amountTrans = 0;
      this.locationIP = '';
      this.isSuspicious = false;
    }
}