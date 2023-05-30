package sbnz.integracija.example.controller;

import demo.facts.Book;
import demo.facts.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.service.BookService;
import sbnz.integracija.example.service.UserService;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    @RequestMapping( method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/createBook", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        Book ret = (Book) bookService.addBook(book);
        if (ret != null)
            return new ResponseEntity<>(ret, HttpStatus.OK);

        return new ResponseEntity<>("Name exist", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping( value = "/unregisteredPopularBooks",method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getUnregisteredPopularBooks() {
        System.out.println("Pogodilo endpoint");
        return new ResponseEntity<>(bookService.getUnregisteredPopularBooks(), HttpStatus.OK);
    }
}
