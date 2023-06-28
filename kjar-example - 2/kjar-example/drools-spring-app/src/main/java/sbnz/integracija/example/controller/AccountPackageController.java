package sbnz.integracija.example.controller;

import demo.facts.AccountPackage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.service.AccountPackageService;

import java.util.UUID;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4201", maxAge = 3600)
@RestController
@RequestMapping("/accountPackages")
public class AccountPackageController {
    @Autowired
    private final AccountPackageService accountPackageService;

    @RequestMapping(value = "/addPackage", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addPackage(@RequestBody AccountPackage accountPackage) {
        accountPackage.setId(UUID.randomUUID());
        return new ResponseEntity<>(accountPackageService.addPackage(accountPackage), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> packagesByUser(@RequestParam("id") String email) {
        return new ResponseEntity<>(accountPackageService.getPackagesByUser(email), HttpStatus.OK);
    }
}
