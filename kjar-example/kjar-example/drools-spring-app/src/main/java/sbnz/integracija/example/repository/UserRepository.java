package sbnz.integracija.example.repository;

import demo.facts.BookCategory;
import demo.facts.Role;
import demo.facts.User;
import demo.facts.UserBookRatingDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

        User user1 = new User("lazar@gmail.com","lazar","Lazar", "Mijatovic" , "566566", Role.USER, genres, 0.0, new ArrayList<UserBookRatingDTO>());
        User user2 = new User("none@gmail.com","none","none", "none" , "566566", Role.ADMIN );
        User user3 = new User("user@gmail.com","user","Lazar", "Mijatovic" , "566566", Role.USER);


        this.users.add(user1);
        this.users.add(user2);
        this.users.add(user3);

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
