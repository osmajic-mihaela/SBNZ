package sbnz.integracija.example.repository;

import demo.facts.Book;
import demo.facts.BookCategory;
import demo.facts.BookRatingCategory;
import demo.facts.Writer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BookRepository {


    private static BookRepository instance = new BookRepository();
    private List<Book> books;
    private List<Writer> writers;

    public static BookRepository getInstance() {
        return instance;
    }
    public BookRepository(){

        init();

    }

    private void init(){
        this.books = new ArrayList<>();
        this.writers = new ArrayList<>();

        Writer writer1 = new Writer("Harper Lee");
        Writer writer2 = new Writer("J.D. Salinger");
        Writer writer3 = new Writer("Patak Daca");


        Book book1 = new Book( "To Kill a Mockingbird",2000.0,"Harper Lee", BookCategory.NOVELS, new Date(123, 3, 6),false, false,4.5,21, BookRatingCategory.NEUTRAL,new Date(122, 3, 6));
        Book book2 = new Book( "The Catcher in the Rye",1000.0,"J.D. Salinger",BookCategory.EDUCATION,new Date(123, 4, 28),false,false,1.0,2,BookRatingCategory.NEUTRAL,new Date(122, 3, 6));
        Book book3 = new Book("Other Book", 1500.0, "Some Author", BookCategory.NOVELS, new Date(122, 3, 6),false,false,0.0,0,BookRatingCategory.NEUTRAL, new Date(122, 3, 6));

        writer1.getBooks().add(book1);
        writer2.getBooks().add(book2);
        writer3.getBooks().add(book3);

        this.books.add(book1);
        this.books.add(book2);
        this.books.add(book3);

        this.writers.add(writer1);
        this.writers.add(writer2);
        this.writers.add(writer3);



        for(int i=0; i!=10; i++){
            this.books.add(new Book("TEMP"+i, 1500.0, "Some Author", BookCategory.NOVELS, new Date(123, 4, 28),true,false,0.0,0,BookRatingCategory.NEUTRAL, new Date(122, 3, 6),writer3));
        }
    }

    public List<Book> getBooks(){
        return books;
    }

    public List<Writer> getWriters(){
        return writers;
    }


    public void addBook(Book book){
        this.books.add(book);
    }


}
