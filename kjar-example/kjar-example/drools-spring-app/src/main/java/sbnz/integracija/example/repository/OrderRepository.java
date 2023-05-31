package sbnz.integracija.example.repository;

import demo.facts.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
    private static OrderRepository instance = new OrderRepository();
    private List<Order> orders;

    public static OrderRepository getInstance() {
        return instance;
    }
    public OrderRepository(){
        init();
    }

    private void init(){
        this.orders = new ArrayList<>();

        Writer writer1 = new Writer("Harper Lee");
        Writer writer2 = new Writer("J.D. Salinger");
        Writer writer3 = new Writer("Patak Daca");

        Book book1 = new Book( "To Kill a Mockingbird",2000.0,"Harper Lee", BookCategory.NOVELS, new Date(123, 3, 6),false, false,4.5,21, BookRatingCategory.NEUTRAL,new Date(122, 3, 6));
        Book book2 = new Book( "The Catcher in the Rye",1000.0,"J.D. Salinger",BookCategory.EDUCATION,new Date(123, 4, 28),false,false,1.0,2,BookRatingCategory.NEUTRAL,new Date(122, 3, 6));
        Book book3 = new Book("Other Book", 1500.0, "Some Author", BookCategory.NOVELS, new Date(122, 3, 6),false,false,0.0,0,BookRatingCategory.NEUTRAL, new Date(122, 3, 6));



        OrderItem userBook1 = new OrderItem( "To Kill a Mockingbird",2, book1);
        OrderItem userBook2 = new OrderItem( "The Catcher in the Rye",1,book2);
        ArrayList<OrderItem> items = new ArrayList<OrderItem>();
        items.add(userBook1);
        items.add(userBook2);

        this.orders.add(new Order("none@gmail.com",items));

    }
    public List<Order> getOrders(){
        return orders;
    }

    public Order getOrderById(UUID id)
    {
        return orders.stream().filter(u->u.getOrderId().equals(id)).findFirst().orElse(null);
    }

    public ArrayList<Order> getOrdersByUser(String email)
    {
        return (ArrayList<Order>) orders.stream()
                .filter(order -> order.getUserEmail().equals(email))
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersByUserSortedByDate(String userEmail) {
        return orders.stream()
                .filter(order -> order.getUserEmail().equals(userEmail))
                .sorted(Comparator.comparing(Order::getOrderDate))
                .collect(Collectors.toList());
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

}
