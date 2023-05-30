import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { BooksComponent } from './components/books/books.component';
import { CreateBookComponent } from './components/books/create-book/create-book.component';
import { PopularBooksComponent } from './components/books/popular-books/popular-books.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegistrationComponent},
  { path: 'books', component: BooksComponent},
  { path: 'add-book', component: CreateBookComponent},
  { path: 'recommended-books', component: PopularBooksComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
