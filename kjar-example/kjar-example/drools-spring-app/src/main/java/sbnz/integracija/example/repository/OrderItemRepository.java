package sbnz.integracija.example.repository;

import demo.facts.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderItemRepository {

    private static OrderItemRepository instance = new OrderItemRepository();
    private List<OrderItem> orderItems;

    public static OrderItemRepository getInstance() {
        return instance;
    }
    public OrderItemRepository(){
        init();
    }

    private void init(){
        this.orderItems = new ArrayList<>();
        OrderItem userBook1 = new OrderItem( "To Kill a Mockingbird",2, new Book( "To Kill a Mockingbird",2000.0,"Harper Lee", BookCategory.NOVELS));
        OrderItem userBook2 = new OrderItem( "The Catcher in the Rye",1,new Book( "The Catcher in the Rye",1000.0,"J.D. Salinger",BookCategory.EDUCATION));

        this.orderItems.add(userBook1);
        this.orderItems.add(userBook2);
    }
    public List<OrderItem> getOrderItems(){
        return orderItems;
    }

    public OrderItem getOrderItem(UUID id)
    {
        return orderItems.stream().filter(u->u.getOrderId().equals(id)).findFirst().orElse(null);
    }

    public OrderItem addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        return orderItem;
    }
}
