package sbnz.integracija.example.service;

import demo.facts.User;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.dto.LoginDTO;
import sbnz.integracija.example.repository.UserRepository;

import java.util.List;

@Service
public class UserService {



    private final KieContainer kieContainer;

    private final UserRepository repository = UserRepository.getInstance();

    @Autowired
    public UserService(KieContainer kieContainer) {

        this.kieContainer = kieContainer;
    }

    public List<User> getUsers() {

        return repository.getUsers();
    }

    public Object login(LoginDTO dto) {
        List<User> users = repository.getUsers();
        User retVal = users.stream().filter(u->u.getEmail().trim().equals(dto.email.trim()) && u.getPassword().trim().equals(dto.password.trim())).findFirst().orElse(null);

        if (retVal != null) {
            this.repository.setLoggedUser(retVal);
            return retVal;
        }
        else if (users.stream().filter(u->u.getEmail().trim().equals(dto.email.trim())).findFirst().orElse(null) != null) {
            return "\"" + " Password is incorect !" + "\"";
        }
        else {
            return "\"" + " Email you entered does not exist !" + "\"";
        }
    }



    public Object register(User user) {

        List<User> users = repository.getUsers();
        User retVal = users.stream().filter(u->u.getEmail().equals(user.getEmail())).findFirst().orElse(null);

        if (retVal == null) {
            this.repository.addUser(user);
            return user;
        }
        else {
            return "\"" + " Email you entered already exist !" + "\"";
        }
    }

    public Object getLoggedUser(){
        return repository.getLoggedUser();
    }

    public User logout(){
        return repository.logout();
    }


}
