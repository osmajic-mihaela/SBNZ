package sbnz.integracija.example.service;

import demo.facts.Order;
import demo.facts.OrderItem;
import demo.facts.ShoppingDto;
import demo.facts.User;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import sbnz.integracija.example.repository.OrderRepository;
import sbnz.integracija.example.repository.UserRepository;

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

        //obrada transkacije
        String url = "https://localhost:8081/transactions/";
        var restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody;
        try {
            //promeni ovo u transactions
            requestBody = objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        String response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
        System.out.println("Transaction response: " + response);
        /////////////////////////////////////////////////

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
