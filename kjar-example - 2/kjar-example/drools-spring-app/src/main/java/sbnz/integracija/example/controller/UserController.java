package sbnz.integracija.example.controller;

import demo.facts.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.dto.LoginDTO;
import sbnz.integracija.example.service.UserService;

import java.util.List;


@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4201", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {



    //  private static Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private final UserService userService;


    @RequestMapping( method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAll() {
        List<User> u =  userService.getUsers();
        return u;
        //return new ResponseEntity<>("{Hej}", HttpStatus.OK);
    }

    @RequestMapping( value = "/loggedUser",method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLoggedUser() {
        return new ResponseEntity<>(userService.getLoggedUser(), HttpStatus.OK);
    }

    @RequestMapping( value = "/logout",method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logout() {
        return new ResponseEntity<>(userService.logout(), HttpStatus.OK);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        return new ResponseEntity<>(userService.login(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody User user) {
        return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
    }



}
