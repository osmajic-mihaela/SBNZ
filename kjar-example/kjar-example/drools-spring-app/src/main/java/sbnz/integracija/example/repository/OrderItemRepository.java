package sbnz.integracija.example.repository;

import demo.facts.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
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

        Writer writer1 = new Writer("Harper Lee");
        Writer writer2 = new Writer("J.D. Salinger");
        Writer writer3 = new Writer("Patak Daca");

        Book book1 = new Book( "To Kill a Mockingbird",2000.0,"Harper Lee", BookCategory.NOVELS, new Date(123, 3, 6),false, false,4.5,21, BookRatingCategory.NEUTRAL,new Date(122, 3, 6));
        Book book2 = new Book( "The Catcher in the Rye",1000.0,"J.D. Salinger",BookCategory.EDUCATION,new Date(123, 4, 28),false,false,1.0,2,BookRatingCategory.NEUTRAL,new Date(122, 3, 6));
        Book book3 = new Book("Other Book", 1500.0, "Some Author", BookCategory.NOVELS, new Date(122, 3, 6),false,false,0.0,0,BookRatingCategory.NEUTRAL, new Date(122, 3, 6));


        OrderItem userBook1 = new OrderItem( "To Kill a Mockingbird",2, book1);
        OrderItem userBook2 = new OrderItem( "The Catcher in the Rye",1,book2);

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
