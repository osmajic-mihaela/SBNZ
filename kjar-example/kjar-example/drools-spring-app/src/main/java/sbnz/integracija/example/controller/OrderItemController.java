package sbnz.integracija.example.controller;

import demo.facts.Book;
import demo.facts.OrderItem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.dto.OrderItemDTO;
import sbnz.integracija.example.service.OrderItemService;
import sbnz.integracija.example.service.UserService;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
    @Autowired
    private final OrderItemService orderItemService;

    @RequestMapping(value = "/createOrderItem", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createBook(@RequestBody OrderItemDTO item) {
        OrderItem ret = (OrderItem) orderItemService.addOrderItem(item);
        if (ret != null)
            return new ResponseEntity<>(ret, HttpStatus.OK);

        return new ResponseEntity<>("Name exist", HttpStatus.BAD_REQUEST);
    }

}
