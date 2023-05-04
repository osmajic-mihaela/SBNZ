package sbnz.integracija.example.service;

import demo.facts.Book;
import demo.facts.Role;
import demo.facts.User;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.repository.BookRepository;
import sbnz.integracija.example.repository.UserRepository;

import java.util.List;

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

        return repository.getBooks();
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
}
