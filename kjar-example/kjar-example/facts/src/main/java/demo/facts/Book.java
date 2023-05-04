package demo.facts;

import java.io.Serializable;

public class Book implements Serializable {
    private String name;
    private Double price;
    private String writer;

    private BookCategory category;

    public Book(String name, Double price, String writer, BookCategory category) {
        this.name = name;
        this.price = price;
        this.writer = writer;
        this.category = category;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getWriter() {
        return writer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }
}
