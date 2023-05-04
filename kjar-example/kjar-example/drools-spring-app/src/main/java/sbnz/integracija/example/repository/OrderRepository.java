package sbnz.integracija.example.repository;

import demo.facts.Book;
import demo.facts.BookCategory;
import demo.facts.Order;
import demo.facts.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
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
        OrderItem userBook1 = new OrderItem( "To Kill a Mockingbird",2, new Book( "To Kill a Mockingbird",2000.0,"Harper Lee", BookCategory.NOVELS));
        OrderItem userBook2 = new OrderItem( "The Catcher in the Rye",1,new Book( "The Catcher in the Rye",1000.0,"J.D. Salinger",BookCategory.EDUCATION));
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
