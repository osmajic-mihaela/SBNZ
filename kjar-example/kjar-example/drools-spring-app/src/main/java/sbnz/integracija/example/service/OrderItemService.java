package sbnz.integracija.example.service;

import demo.facts.OrderItem;
import demo.facts.User;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.repository.OrderItemRepository;

import java.util.List;
import java.util.UUID;

@Service
public class OrderItemService {

    private final KieContainer kieContainer;

    private final OrderItemRepository repository = OrderItemRepository.getInstance();

    @Autowired
    public OrderItemService(KieContainer kieContainer) {

        this.kieContainer = kieContainer;
    }

    public List<OrderItem> getOrderItems() {
        return repository.getOrderItems();
    }

    public OrderItem getOrderItem(UUID id) {
        return repository.getOrderItem(id);
    }


    public Object addOrderItem(OrderItem orderItem) {
        this.repository.addOrderItem(orderItem);
        return orderItem;
    }
}
