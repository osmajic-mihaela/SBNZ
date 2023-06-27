package sbnz.integracija.example.service;

import demo.facts.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sbnz.integracija.example.repository.OrderRepository;
import sbnz.integracija.example.repository.UserRepository;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final KieContainer kieContainer;
    private final OrderRepository repository = OrderRepository.getInstance();
    private final UserRepository userRepository = UserRepository.getInstance();

    @Autowired
    public OrderService(KieContainer kieContainer) {

        this.kieContainer = kieContainer;
    }

    public List<Order> getOrders() {
        return repository.getOrders();
    }

    public Order getOrder(UUID id) {
        return repository.getOrderById(id);
    }

    public List<Order> getOrdersByUser(String email) {
        return repository.getOrdersByUser(email);
    }
    public List<Order> getOrdersByUserSortedByDate(String email) {
        return repository.getOrdersByUserSortedByDate(email);
    }

    public Order addOrderCard(Order order, Transaction transaction)
    {
        KieSession kieSession = kieContainer.newKieSession("classify-item-rules");

        for(OrderItem item:order.getItems()){
            kieSession.insert(item);
        }

        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();

        double priceWithItems =0.0;
        double priceWithOrder =0.0;

        for(OrderItem item: order.getItems()){
            priceWithItems += item.calculateItemPriceWithDiscount(item.getDiscount());
        }

        priceWithOrder = order.calculatePriceWithDiscount(order.getDiscount());

        if(priceWithOrder<priceWithItems){
            order.setOrderPrice(priceWithOrder);
        }else{
            order.setOrderPrice(priceWithItems);
        }

        return order;
    }

    public Order addOrder(Order order)
    {
        KieSession kieSession = kieContainer.newKieSession("classify-item-rules");

        for(OrderItem item:order.getItems()){
            kieSession.insert(item);
        }

        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();

        double priceWithItems =0.0;
        double priceWithOrder =0.0;

        for(OrderItem item: order.getItems()){
            priceWithItems += item.calculateItemPriceWithDiscount(item.getDiscount());
        }

        priceWithOrder = order.calculatePriceWithDiscount(order.getDiscount());

        if(priceWithOrder<priceWithItems){
            order.setOrderPrice(priceWithOrder);
        }else{
            order.setOrderPrice(priceWithItems);
        }

        User u = userRepository.getLoggedUser();
        for(OrderItem item:order.getItems()){
            ShoppingDto dto = new ShoppingDto();
            dto.book =item.getBook();
            dto.date = new Date();
            u.getShoppedBooks().add(dto);
        }

        repository.addOrder(order);
        return order;
    }



}
