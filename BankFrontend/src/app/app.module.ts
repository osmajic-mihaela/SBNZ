import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AuthGuard } from './service/auth.guard';
import { BankAccountsComponent } from './bank-accounts/bank-accounts.component';
import { ClientLoansComponent } from './client-loans/client-loans.component';
import { ClientTransactionsComponent } from './client-transactions/client-transactions.component';

const routes: Routes = [
  {
    path: 'register',
    component: RegistrationComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'bank-accounts',
    component: BankAccountsComponent
  },
  {
    path: 'loan-requests',
    component: ClientLoansComponent
  },
  {
    path: 'transactions',
    component: ClientTransactionsComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    NavbarComponent,
    BankAccountsComponent,
    ClientLoansComponent,
    ClientTransactionsComponent
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
