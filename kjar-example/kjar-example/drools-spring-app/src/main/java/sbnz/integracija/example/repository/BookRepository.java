package sbnz.integracija.example.repository;

import demo.facts.Book;
import demo.facts.BookCategory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {


    private static BookRepository instance = new BookRepository();
    private List<Book> books;

    public static BookRepository getInstance() {
        return instance;
    }
    public BookRepository(){

        init();

    }

    private void init(){
        this.books = new ArrayList<>();
        Book book1 = new Book( "To Kill a Mockingbird",2000.0,"Harper Lee", BookCategory.NOVELS);
        Book book2 = new Book( "The Catcher in the Rye",1000.0,"J.D. Salinger",BookCategory.EDUCATION);

        this.books.add(book1);
        this.books.add(book2);
    }

    public List<Book> getBooks(){
        return books;
    }


    public void addBook(Book book){
        this.books.add(book);
    }

}
