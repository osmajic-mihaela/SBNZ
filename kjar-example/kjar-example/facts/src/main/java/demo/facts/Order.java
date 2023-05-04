package demo.facts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order implements Serializable {

    private UUID orderId;
    private List<UUID> itemsIDs;
    private List<OrderItem> items;
    private double orderPrice;
    private String userEmail;

    private Date orderDate;

    private int discount;

    public Order(String userEmail,ArrayList<OrderItem> orderItems) {
        this.orderId = UUID.randomUUID();
        this.userEmail = userEmail;
        this.items = orderItems;
        this.itemsIDs = new ArrayList<UUID>();
        this.orderPrice = 0.0;
        for (OrderItem item: items) {
            itemsIDs.add(item.getOrderId());
            orderPrice += item.getOrderItemPrice();
        }
        this.orderDate = new Date();
        this.discount = 0;
    }

    public Order() {
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public List<UUID> getItemsIDs() {
        return itemsIDs;
    }

    public void setItemsIDs(List<UUID> itemsIDs) {
        this.itemsIDs = itemsIDs;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getOrderDate() {return orderDate;}

    public void setOrderDate(Date orderDate) { this.orderDate = orderDate;}
    public Double calculatePriceWithDiscount(int discount){
        return orderPrice - orderPrice*discount/100;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
