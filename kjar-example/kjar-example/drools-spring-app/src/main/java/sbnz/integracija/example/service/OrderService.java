package sbnz.integracija.example.service;

import demo.facts.Order;
import demo.facts.OrderItem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.repository.OrderItemRepository;
import sbnz.integracija.example.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final KieContainer kieContainer;
    private final OrderRepository repository = OrderRepository.getInstance();

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
        KieSession kieSession = kieContainer.newKieSession();

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



        repository.addOrder(order);
        return order;
    }

}
