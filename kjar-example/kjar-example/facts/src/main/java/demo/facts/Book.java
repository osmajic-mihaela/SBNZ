package demo.facts;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Book implements Serializable {
    private String name;
    private Double price;
    private String writer;

    private BookCategory category;
    private Date bookDate;
    private Date bookReleaseDate;
    private boolean isNew;
    private boolean isPopular;
    private BookRatingCategory ratingCategory;
    private double rating;
    private int numberOfRatings;

    public Book(String name, Double price, String writer, BookCategory category, Date bookReleaseDate) {
        this.name = name;
        this.price = price;
        this.writer = writer;
        this.category = category;
        this.bookDate = new Date();
        this.isNew = true;
        this.isPopular = false;
        this.rating = 0.0;
        this.ratingCategory = BookRatingCategory.NEUTRAL;
        this.bookReleaseDate = bookReleaseDate;
        this.numberOfRatings = 0;
    }

    public Book(String name, Double price, String writer, BookCategory category, Date date, boolean isNew, boolean isPopular, double rating,int numberOfRatings ,BookRatingCategory bookRatingCategory, Date bookReleaseDate) {
        this.name = name;
        this.price = price;
        this.writer = writer;
        this.category = category;
        this.bookDate = date;
        this.isNew = isNew;
        this.isPopular = isPopular;
        this.rating = rating;
        this.ratingCategory = bookRatingCategory;
        this.bookReleaseDate = bookReleaseDate;
        this.numberOfRatings = numberOfRatings;
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

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean getIsPopular() {
        return isPopular;
    }

    public void setPopular(boolean popular) {
        isPopular = popular;
    }

    public BookRatingCategory getRatingCategory() {
        return ratingCategory;
    }

    public void setRatingCategory(BookRatingCategory ratingCategory) {
        this.ratingCategory = ratingCategory;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getBookReleaseDate() {
        return bookReleaseDate;
    }

    public void setBookReleaseDate(Date bookReleaseDate) {
        this.bookReleaseDate = bookReleaseDate;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }
}
