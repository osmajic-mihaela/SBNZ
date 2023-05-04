package demo.facts;

import java.io.Serializable;
import java.util.UUID;

public class OrderItem implements Serializable {

        private UUID orderId;
        private String bookName;
        private  int quantity;
        private Book book;

        private Double orderItemPrice;

        private int discount;

    public OrderItem(String bookName, int quantity, Book book){
        this.orderId = UUID.randomUUID();
        this.bookName = bookName;
        this.quantity = quantity;
        this.book = book;
        this.orderItemPrice = book.getPrice() * quantity;
        this.discount = 0;
    }

    public OrderItem(){

    }



    public String getBookName() {
        return bookName;
    }
    public int getQuantity(){return quantity;}

    public Book getBook(){return book;}

    public Double getOrderItemPrice(){return orderItemPrice;}

    public UUID getOrderId(){return orderId;}

    public Double calculateItemPriceWithDiscount(int discount){
        return orderItemPrice - orderItemPrice* discount/100;
    }


    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public void setQuantity(int quantity){this.quantity =quantity;}
    public void setBook(Book book){this.book = book;}

    public void setOrderItemPrice(Double orderPrice){this.orderItemPrice = orderPrice;}

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
