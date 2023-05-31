package demo.facts;

import java.util.ArrayList;
import java.util.List;

public class Writer {

    private String writer;
    private List<Book> books;
    private ArrayList<BookCategory> genres;
    private double rating;


    public Writer(){}

    public Writer(String writer, List<Book> books, ArrayList<BookCategory> genres, double rating){
        this.writer = writer;
        this.books = books;
        this.genres = genres;
        this.rating = rating;
    }

    public Writer(String writer, List<Book> books){
        this.writer = writer;
        this.books = books;
        this.genres = new ArrayList<BookCategory>();
        this.rating = 0.0;
    }

    public Writer(String writer){
        this.writer = writer;
        this.books = new ArrayList<>();
        this.genres = new ArrayList<BookCategory>();
        this.rating = 0.0;
    }


    public Writer(Writer writer){
        this.writer = writer.writer;
        this.books = writer.books;
        this.genres =writer.genres;
        this.rating = writer.rating;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public ArrayList<BookCategory> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<BookCategory> genres) {
        this.genres = genres;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
