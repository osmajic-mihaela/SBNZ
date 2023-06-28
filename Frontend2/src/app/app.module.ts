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
import { AuthGuard } from './services/auth.guard';
import { UserRole } from './model/user.model';
import { CreditApprovalComponent } from './credit-approval/credit-approval.component';

const routes: Routes = [
  { 
    path: 'login', 
    component: LoginComponent,
  },
  { 
    path: 'register', 
    component: RegistrationComponent
  },
  { 
    path: 'bank-accounts', 
    component: BankAccountsComponent,
    canActivate: [AuthGuard],
    data: {
      role: UserRole.USER
    }
  },
  { 
    path: 'loan-requests',
    component: ClientLoansComponent,
    canActivate: [AuthGuard],
    data: {
      role: UserRole.USER
    }
  },
  { 
    path: 'transactions', 
    component: ClientTransactionsComponent,
    canActivate: [AuthGuard],
    data: {
      role: UserRole.USER
    }
  },
  {
    path: 'credit-approval',
    component: CreditApprovalComponent,
    canActivate: [AuthGuard],
    data: {
      role: UserRole.ADMIN
    }
  }

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
    LoanApprovalComponent,
    CreditApprovalComponent
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
