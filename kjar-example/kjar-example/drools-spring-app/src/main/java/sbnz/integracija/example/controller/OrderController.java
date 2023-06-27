package sbnz.integracija.example.controller;

import demo.facts.Book;
import demo.facts.Order;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.dto.LoginDTO;
import sbnz.integracija.example.dto.OrderDTO;
import sbnz.integracija.example.service.OrderService;
import sbnz.integracija.example.service.UserService;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    @RequestMapping( value = "/userOrdersSorted",method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getUserOrdersByDate(LoginDTO dto) {
        return new ResponseEntity<>(orderService.getOrdersByUserSortedByDate(dto.email), HttpStatus.OK);
    }



    @RequestMapping(value = "/createOrder", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO dto) {
        Order order = new Order(dto.userEmail, dto.items);
        return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.OK);
    }

    @RequestMapping(value = "/createOrderCard", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createOrderCard(@RequestBody OrderDTO dto) {
        Order order = new Order(dto.userEmail, dto.items);
        System.out.println(order);
        return new ResponseEntity<>(orderService.addOrderCard(order,dto.transaction), HttpStatus.OK);
    }
}
