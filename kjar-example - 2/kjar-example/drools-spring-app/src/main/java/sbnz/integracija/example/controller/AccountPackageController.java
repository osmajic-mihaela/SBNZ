package sbnz.integracija.example.controller;

import demo.facts.AccountPackage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.service.AccountPackageService;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4201", maxAge = 3600)
@RestController
@RequestMapping("/accountPackages")
public class AccountPackageController {
    @Autowired
    private final AccountPackageService accountPackageService;

    @RequestMapping(value = "/addPackage", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addPackage(@RequestBody AccountPackage accountPackage) {
        return new ResponseEntity<>(accountPackageService.addPackage(accountPackage), HttpStatus.OK);
    }
}
