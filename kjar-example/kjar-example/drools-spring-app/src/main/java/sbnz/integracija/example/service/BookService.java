package sbnz.integracija.example.service;

import demo.facts.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.repository.BookRepository;
import sbnz.integracija.example.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class BookService {
    private final KieContainer kieContainer;

    private final BookRepository repository = BookRepository.getInstance();
    private final UserRepository userRepository = UserRepository.getInstance();

    @Autowired
    public BookService(KieContainer kieContainer) {

        this.kieContainer = kieContainer;
    }

    public List<Book> getBooks() {
        KieSession kieSession = kieContainer.newKieSession("book-recommendation-rules");
        List<Book> books = repository.getBooks();
        List<Book> popularBooks = new ArrayList<Book>();
        kieSession.setGlobal("popularBooks", popularBooks);
        for(Book book:books){
            kieSession.insert(book);
        }

        kieSession.fireAllRules();
        kieSession.dispose();
        return books;
    }

    public Object getBook(String bookName) {
        List<Book> books = getBooks();
        Book retVal = books.stream().filter(u->u.getName().trim().equals(bookName.trim())).findFirst().orElse(null);

        if (retVal != null) {
            return "\"" + " Book doesn't exist !" + "\"";
        }

        return retVal;
    }



    public Object addBook(Book book) {

        if(userRepository.getLoggedUser().getRole() != Role.ADMIN){
            return "\"" + "You are not allowed!" + "\"";
        }

        List<Book> books =  getBooks();
        Book retVal = books.stream().filter(u->u.getName().equals(book.getName())).findFirst().orElse(null);

        if (retVal == null) {
            this.repository.addBook(book);
            return book;
        }
        else {
            return null;
        }
    }

    public List<Book> getUnregisteredPopularBooks() {
        List<Book> books = getBooks();
        List<Book> popularBooks = new ArrayList<Book>();

        KieSession kieSession = kieContainer.newKieSession("book-recommendation-rules");
        kieSession.setGlobal("popularBooks", popularBooks);


        for(Book book:books){
            kieSession.insert(book);
        }

        kieSession.fireAllRules();
        kieSession.dispose();

        for(Book book:popularBooks){
            System.out.println(book.getName());
        }
        return popularBooks;
    }
}
