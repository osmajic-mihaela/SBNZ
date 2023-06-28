package sbnz.integracija.example.controller;

import demo.facts.CreditRequest;
import demo.facts.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.dto.LoginDTO;
import sbnz.integracija.example.dto.TransactionDTO;
import sbnz.integracija.example.service.CreditService;
import sbnz.integracija.example.service.TransactionService;

import javax.websocket.server.PathParam;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4201", maxAge = 3600)
@RestController
@RequestMapping("/credits")
public class CreditController {

    @Autowired
    private final CreditService creditService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(creditService.getAllRequests(), HttpStatus.OK);
    }
    @RequestMapping(value = "/addRequest", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addRequest(@RequestBody CreditRequest request) {
        request.setId(UUID.randomUUID());
        var ret = creditService.addCreditRequest(request);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @RequestMapping(value = "/getPendingRequest", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getPending(@RequestBody LoginDTO dto) {
        var ret = creditService.getClientPendingRequests(dto.email);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @RequestMapping(value = "/acceptRequest/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?>acceptRequest(@PathVariable("id") String id) {
        var ret = creditService.acceptRequest(UUID.fromString(id));
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @RequestMapping(value = "/rejectRequest/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> rejectRequest(@PathVariable("id") String id) {
        var ret = creditService.rejectRequest(UUID.fromString(id));
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

}
