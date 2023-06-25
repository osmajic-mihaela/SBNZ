package sbnz.integracija.example.controller;

import demo.facts.AccountPackage;
import demo.facts.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.service.TransactionService;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4201", maxAge = 3600)
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private final TransactionService transactionService;

    @RequestMapping(value = "/addTransaction", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction) {
        var ret = transactionService.addTransaction(transaction);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
