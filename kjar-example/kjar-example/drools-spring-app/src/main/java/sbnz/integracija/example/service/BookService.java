package sbnz.integracija.example.service;

import demo.facts.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.dto.BookRateDTO;
import sbnz.integracija.example.repository.BookRepository;
import sbnz.integracija.example.repository.UserRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        kieSession.setGlobal("currentLog", userRepository.getLoggedUser());

        User unregistered = new User("unregistered", "unregistered", "unregistered", "unregistered", "unregistered", Role.UNREGISTERED);
        kieSession.insert(unregistered);

        for(Book book:books){
            kieSession.insert(book);
        }

        kieSession.fireAllRules();
        kieSession.dispose();
        return books;
    }

    public List<Writer> getWriters() {
        return repository.getWriters();

    }

    public User getLoggedUser() {
        return userRepository.getLoggedUser();

    }

    public Object getWriter(String name) {
        List<Writer> writers = getWriters();
        Writer retVal = writers.stream().filter(u->u.getWriter().trim().equals(name.trim())).findFirst().orElse(null);

        if (retVal != null) {
            return retVal;
        }

        return null;
    }

    public Object getBook(String bookName) {
        List<Book> books = getBooks();
        Book retVal = books.stream().filter(u->u.getName().trim().equals(bookName.trim())).findFirst().orElse(null);

        if (retVal != null) {
            return "\"" + " Book doesn't exist !" + "\"";
        }

        return retVal;
    }

    public Object rateBook(BookRateDTO dto) {
        User logged = userRepository.getLoggedUser();
        System.out.println(logged.getName());

        List<Book> books = getBooks();
        Book book = books.stream().filter(u->u.getName().trim().equals(dto.book.trim())).findFirst().orElse(null);
        System.out.println(book.getName());
        double rating = book.getRating();
        int nmb= book.getNumberOfRatings();

        book.setNumberOfRatings(nmb+1);
        System.out.println(nmb+" nmb");
        System.out.println(rating+" rating");
        System.out.println(book.getNumberOfRatings()+" nmbg rating");
        if(nmb!=0){
            book.setRating((rating*nmb+ (int)dto.rating)/ book.getNumberOfRatings());
            System.out.println(dto.rating+"u ifuuu");
        }else{
            book.setRating(dto.rating);
        }
        System.out.println(book.getRating());

        UserBookRatingDTO dt = new UserBookRatingDTO();
        dt.book = null;
        dt.bookName = book.getName();
        dt.user = null;
        dt.userEmail = logged.getEmail();
        dt.rating = dto.rating;

        book.getDtos().add(dt);
        getLoggedUser().getBooksAnRatingsDtos().add(dt);

        if(dt.rating >3){
            logged.getLikedBook().add(book);
        }
        logged.setNumberOfRatings(logged.getNumberOfRatings()+1);
        if(logged.getNumberOfRatings()>=10){
            logged.setNew(false);
        }

        Writer wr = (Writer) getWriter(book.getWriter());
        if(wr!=null){
            wr.setRating(wr.getRating()-rating+book.getRating());
        }

        ArrayList<String> w = new ArrayList<>();
        for(Writer mmm: repository.getWriters()){
            w.add(mmm.getWriter());
        }
        KieSession kieSession = kieContainer.newKieSession("user-rules");
        kieSession.setGlobal("writers", w);
        kieSession.insert(logged);
        kieSession.fireAllRules();
        kieSession.dispose();

        if (book == null) {
            return null;
        }

        return book;
    }



    public Object addBook(Book book) {

        if(userRepository.getLoggedUser().getRole() != Role.ADMIN){
            return "\"" + "You are not allowed!" + "\"";
        }

        List<Book> books =  getBooks();
        Book retVal = books.stream().filter(u->u.getName().equals(book.getName())).findFirst().orElse(null);

        if (retVal == null) {
            this.repository.addBook(book);

            Writer wr = (Writer) getWriter(book.getWriter());
            wr.getBooks().add(book);
            wr.getGenres().clear();

            KieSession kieSession = kieContainer.newKieSession("writer-rules");
            kieSession.insert(wr);
            kieSession.fireAllRules();
            kieSession.dispose();

            ArrayList<String> w = new ArrayList<>();
            for(Writer mmm: repository.getWriters()){
                w.add(mmm.getWriter());
            }

            KieSession kieSession1 = kieContainer.newKieSession("user-rules");
            kieSession1.setGlobal("writers", w);
            kieSession1.insert(getLoggedUser());
            kieSession1.fireAllRules();
            kieSession1.dispose();


            List<Book> popularBooks = new ArrayList<Book>();
            KieSession kieSession2 = kieContainer.newKieSession("book-recommendation-rules");
            kieSession2.setGlobal("popularBooks", popularBooks);

            User unregistered = new User("unregistered", "unregistered", "unregistered", "unregistered", "unregistered", Role.UNREGISTERED);
            kieSession.setGlobal("currentLog", unregistered);
            kieSession2.insert(unregistered);
            for(Book b:books){
                kieSession2.insert(b);
            }
            kieSession2.fireAllRules();
            kieSession2.dispose();

            System.out.println(wr.getGenres().size());
            for(BookCategory c:wr.getGenres())
                System.out.println(c);
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


        User unregistered = new User("unregistered", "unregistered", "unregistered", "unregistered", "unregistered", Role.UNREGISTERED);
        kieSession.setGlobal("currentLog", unregistered);
        kieSession.insert(unregistered);
        for(Book book:books){
            kieSession.insert(book);
        }

        kieSession.fireAllRules();
        kieSession.dispose();

        return popularBooks;
    }


    public List<Book> getRegisteredPopularBooks() {
        List<Book> books = getBooks();
        List<Book> popularBooks = new ArrayList<Book>();



        User u = getLoggedUser();
        if(u.getIsNew() == true && u.getFavoriteGenre().size()>0){
            KieSession kieSession = kieContainer.newKieSession("book-recommendation-rules");
            System.out.println("Usloo");
            kieSession.setGlobal("popularBooks", popularBooks);
            kieSession.setGlobal("currentLog", u);

            for(Book book:books){
                Book b= new Book(book);
                kieSession.insert(b);
            }
            for(Writer w:getWriters()){
                System.out.println(w.getGenres().size());
                kieSession.insert(new Writer(w));
            }
            kieSession.insert(u);
            kieSession.getAgenda().getAgendaGroup("rng").setFocus();
            kieSession.fireAllRules();
            kieSession.dispose();

            System.out.println("Pre sorta");
            for(Book b: popularBooks){
                System.out.println(b.getName());
            }

            popularBooks.sort(Comparator.comparing(Book::getRating).reversed());
            System.out.println("Posle sorta");
            for(Book b: popularBooks){
                System.out.println(b.getName());
            }
        }else if(u.getIsNew() == true  && u.getFavoriteGenre().size()==0){
            popularBooks = getUnregisteredPopularBooks();
        } else{
            KieSession kieSession = kieContainer.newKieSession("book-recommendation-rules");
            kieSession.setGlobal("popularBooks", popularBooks);
            kieSession.setGlobal("currentLog", u);

            ArrayList<User> users = (ArrayList<User>) userRepository.getUsers();
            for(User user:users){
                if(user.getRole() != Role.ADMIN){
                    kieSession.insert(user);
                }
            }

            for(Book book:books){
                kieSession.insert(book);
            }
            for(Writer w:getWriters()){
                kieSession.insert(new Writer(w));
            }
            kieSession.insert(u);
            kieSession.getAgenda().getAgendaGroup("rog").setFocus();
            kieSession.fireAllRules();
            kieSession.dispose();



            popularBooks.sort(Comparator.comparing(Book::getRating).reversed());

        }




        return popularBooks.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
