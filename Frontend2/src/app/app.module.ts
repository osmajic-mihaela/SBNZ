import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { ClientTransactionsComponent } from './client-transactions/client-transactions.component';
import { ClientLoansComponent } from './client-loans/client-loans.component';
import { BankAccountsComponent } from './bank-accounts/bank-accounts.component';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { LoanApprovalComponent } from './loan-approval/loan-approval.component';

const routes: Routes = [
  { path: '', component: LoginComponent},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegistrationComponent},
  { path: 'bank-accounts', component: BankAccountsComponent },
  { path: 'loan-requests', component: ClientLoansComponent },
  { path: 'transactions', component: ClientTransactionsComponent }

];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegistrationComponent,
    ClientTransactionsComponent,
    ClientLoansComponent,
    BankAccountsComponent,
    LoanApprovalComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
