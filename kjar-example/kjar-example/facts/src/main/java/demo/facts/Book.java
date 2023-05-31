package demo.facts;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Book implements Serializable {
    private String name;
    private Double price;
    private String writer;

    private Writer writerObject;

    private BookCategory category;
    private Date bookDate;
    private Date bookReleaseDate;
    private boolean isNew;
    private boolean isPopular;
    private BookRatingCategory ratingCategory;
    private double rating;
    private int numberOfRatings;
    private List<UserBookRatingDTO> dtos;

    private int points;

    public Book(String name, Double price, String writer, BookCategory category, Date bookReleaseDate,Writer writerObject) {
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
        this.dtos = new ArrayList<>();
        this.points =0;
        this.writerObject = writerObject;
    }

    public Book(String name, Double price, String writer, BookCategory category, Date date, boolean isNew, boolean isPopular, double rating,int numberOfRatings ,BookRatingCategory bookRatingCategory, Date bookReleaseDate, ArrayList<UserBookRatingDTO> dtos,Writer writerObject) {
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
        this.dtos = dtos;
        this.points =0;
        this.writerObject = writerObject;
    }

    public Book(String name, Double price, String writer, BookCategory category, Date date, boolean isNew, boolean isPopular, double rating,int numberOfRatings ,BookRatingCategory bookRatingCategory, Date bookReleaseDate,Writer writerObject) {
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
        this.dtos = new ArrayList<>();
        this.points =0;
        this.writerObject = writerObject;
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
        this.dtos = new ArrayList<>();
        this.points =0;
        this.writerObject = null;
    }

    public Book(Book b) {
        this.name = b.name;
        this.price = b.price;
        this.writer = b.writer;
        this.category = b.category;
        this.bookDate = b.bookDate;
        this.isNew = b.isNew;
        this.isPopular = b.isPopular;
        this.rating = b.rating;
        this.ratingCategory = b.ratingCategory;
        this.bookReleaseDate = b.bookReleaseDate;
        this.numberOfRatings = b.numberOfRatings;
        this.dtos = b.dtos;
        this.points =b.points;
        this.writerObject = null;
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

    public List<UserBookRatingDTO> getDtos() {
        return dtos;
    }

    public void setDtos(List<UserBookRatingDTO> dtos) {
        this.dtos = dtos;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Writer getWriterObject() {
        return writerObject;
    }

    public void setWriterObject(Writer writerObject) {
        this.writerObject = writerObject;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Book other = (Book) obj;
        return Objects.equals(this.getName(), other.getName()) && Objects.equals(this.getWriter(), other.getWriter());
    }
}
