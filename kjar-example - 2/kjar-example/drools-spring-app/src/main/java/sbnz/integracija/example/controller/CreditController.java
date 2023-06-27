package sbnz.integracija.example.controller;


import demo.facts.CreditRequest;
import lombok.AllArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.dto.ApprovalRecommendation;
import sbnz.integracija.example.dto.CreditRequestDTO;
import sbnz.integracija.example.service.CreditService;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4201", maxAge = 3600)
@RestController
@RequestMapping("/credits")
public class CreditController {

    @Autowired
    private final CreditService creditService;

    @PostMapping(value="/check-request", consumes = "application/json")
    public ResponseEntity<ApprovalRecommendation> checkCreditRequest(@RequestBody CreditRequestDTO requestDto){

        boolean shouldApprove;
        try {
           shouldApprove =  creditService.checkCreditRequest(requestDto);

           ApprovalRecommendation recommendation = new ApprovalRecommendation(shouldApprove);

           return new ResponseEntity<>(recommendation, HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
