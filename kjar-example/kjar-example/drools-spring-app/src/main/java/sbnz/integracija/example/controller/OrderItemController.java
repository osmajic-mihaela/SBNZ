package sbnz.integracija.example.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.integracija.example.service.OrderItemService;
import sbnz.integracija.example.service.UserService;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
    @Autowired
    private final OrderItemService orderItemService;

}
