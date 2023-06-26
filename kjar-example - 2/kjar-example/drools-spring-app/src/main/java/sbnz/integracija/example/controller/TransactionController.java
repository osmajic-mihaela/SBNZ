package sbnz.integracija.example.controller;

import demo.facts.AccountPackage;
import demo.facts.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
                dto.getSenderFirstName(), dto.getSenderLastName(), null, dto.getSenderAccountNumber(), dto.getSenderCardNumber(), dto.getBeneficiarAccountNumber(), dto.getCardExpirationDate(), dto.getCvv(), dto.getAmountTrans(), dto.getLocationIP(), dto.isSuspicious());
        var ret = transactionService.addTransaction(transaction);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}