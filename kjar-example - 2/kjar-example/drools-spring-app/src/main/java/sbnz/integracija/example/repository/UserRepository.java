package sbnz.integracija.example.repository;

import demo.facts.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


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

        Date r = new Date();
        r.setYear(2024);

        AccountPackage pack3 = new AccountPackage(UUID.fromString("0018db5f-4c4d-49a3-bd9c-d497b16cd204"),"random", "Lazar","Lazar", 55555,55555,123,50000.0,r);


        User user4 = new User("lazar@gmail.com","proba","Lazar", "Mijatovic" , "566566", Role.USER, new Date(), 2000.0, "",new Date() );
        User user2 = new User("admin","admin","none", "none" , "566566", Role.ADMIN, new Date(), 2000.0, "",new Date() );
        User user3 = new User("user","user","Lazar", "Mijatovic" , "566566", Role.USER, new Date(), 2000.0, "",new Date());
        ArrayList<AccountPackage> p = new ArrayList<>();
        p.add(pack3);
        user3.setAccountPackages(p);

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

    public User getUserByEmail(String email)
    {
        return users.stream().filter(u->u.getEmail().equals(email)).findFirst().orElse(null);
    }

}
