package sbnz.integracija.example.repository;

import demo.facts.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class UserRepository {

    private static UserRepository instance = new UserRepository();
    private List<User> users;

    private User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }


    public static UserRepository getInstance() {
        return instance;
    }

    public UserRepository(){

        initUsers();

    }


    private void initUsers(){
        users = new ArrayList<>();
        ArrayList<BookCategory> genres = new ArrayList<>();
        genres.add(BookCategory.NOVELS);

        Writer writer1 = new Writer("Harper Lee");
        Book book1 = new Book( "To Kill a Mockingbird",2000.0,"Harper Lee", BookCategory.NOVELS, new Date(123, 3, 6),false, false,4.5,21, BookRatingCategory.NEUTRAL,new Date(122, 3, 6),0,false,false);

        ShoppingDto d =new ShoppingDto();
        d.book = book1;
        d.date = new Date();
        ArrayList<ShoppingDto> l = new ArrayList<ShoppingDto>();
        l.add(d);
        l.add(d);
        l.add(d);

        User user1 = new User("lazar@gmail.com",
                "lazar",
                "Lazar",
                "Mijatovic" ,
                "566566",
                Role.USER,
                genres,
                0.0,
                new ArrayList<UserBookRatingDTO>(),
                new ArrayList<BookCategory>(),
                new ArrayList<String>(),
                l
                );
        user1.setNew(false);
        User user4 = new User("proba","proba","Lazar", "Mijatovic" , "566566", Role.USER, genres, 0.0, new ArrayList<UserBookRatingDTO>());
        User user2 = new User("none@gmail.com","none","none", "none" , "566566", Role.ADMIN );
        User user3 = new User("user","user","Lazar", "Mijatovic" , "566566", Role.USER, genres, 0.0, new ArrayList<UserBookRatingDTO>());


        this.users.add(user1);
        this.users.add(user2);
        this.users.add(user3);
        this.users.add(user4);

    }

    public List<User> getUsers(){
        return users;
    }


    public void addUser(User user){
        this.users.add(user);
    }

    public User logout(){
        return this.loggedUser = null;
    }

}
