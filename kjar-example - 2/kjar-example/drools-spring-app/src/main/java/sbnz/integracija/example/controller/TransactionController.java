package sbnz.integracija.example.controller;

import demo.facts.AccountPackage;
import demo.facts.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.dto.LoginDTO;
import sbnz.integracija.example.dto.TransactionDTO;
import sbnz.integracija.example.service.TransactionService;

import java.util.UUID;

@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4201", "http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private final TransactionService transactionService;

    @RequestMapping(value = "/addTransaction", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionDTO dto) {
        System.out.println("UPAOOO U METODUUUUU");
        System.out.println(dto.getLocationIP());
        Transaction transaction = new Transaction(UUID.randomUUID(), dto.getTransactionDate(), dto.getSenderEmail(),
                dto.getSenderFirstName(), dto.getSenderLastName(), null, dto.getSenderAccountNumber(), dto.getSenderCardNumber(), dto.getBeneficiarAccountNumber(), dto.getCardExpirationDate(), dto.getCvv(), dto.getAmountTrans(), dto.getLocationIP(), dto.isSuspicious(), false,false,"books");
        var ret = transactionService.addTransaction(transaction);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @RequestMapping(value = "/suspiciousTransaction", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addTransaction(@RequestBody LoginDTO dto) {
        var ret = transactionService.getClientSuspiciousTransactions(dto.email);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
    @RequestMapping(value = "/approveTransaction/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> approveTransaction(@PathVariable("id") String id)
    {
        return new ResponseEntity<>(transactionService.approveTransaction(id), HttpStatus.OK);
    }
    @RequestMapping(value = "/cancelTransaction/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> cancelTransaction(@PathVariable("id") String id)
    {
        return new ResponseEntity<>(transactionService.cancelTransaction(id), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getClientTransactions(@RequestParam("email") String email)
    {
        return new ResponseEntity<>(transactionService.getTransactionsByClientId(email), HttpStatus.OK);
    }
}
