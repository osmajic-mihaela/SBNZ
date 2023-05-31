package demo.facts;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{

    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNum;

    private Role role;

    private boolean isNew;
    private ArrayList<BookCategory> favoriteGenre;

    private List<Book> likedBook;
    private List<Book> shoppedBooks;
    private double numberOfRatings;
    private List<UserBookRatingDTO> booksAnRatingsDtos;

    public User(String email, String password, String name, String surname, String phoneNum, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNum = phoneNum;
        this.role=role;
        this.isNew = true;
        this.favoriteGenre = new ArrayList<BookCategory>();
        this.likedBook = new ArrayList<Book>();
        this.shoppedBooks = new ArrayList<Book>();
        this.numberOfRatings = 0.0;
        this.booksAnRatingsDtos = new ArrayList<>();
    }

    public User(String email, String password, String name, String surname, String phoneNum, Role role, ArrayList<BookCategory> favoriteGenres, double numberOfRatings, ArrayList<UserBookRatingDTO> dtos) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNum = phoneNum;
        this.role=role;
        this.isNew = true;
        this.favoriteGenre = favoriteGenres;
        this.likedBook = new ArrayList<Book>();
        this.shoppedBooks = new ArrayList<Book>();
        this.numberOfRatings = numberOfRatings;
        this.booksAnRatingsDtos = dtos;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Role getRole(){return role;}


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public  void setRole(Role role){ this.role = role;}

    public boolean getIsNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public ArrayList<BookCategory> getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(ArrayList<BookCategory> favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public List<Book> getLikedBook() {
        return likedBook;
    }

    public void setLikedBook(List<Book> likedBook) {
        this.likedBook = likedBook;
    }

    public List<Book> getShoppedBooks() {
        return shoppedBooks;
    }

    public void setShoppedBooks(List<Book> shoppedBooks) {
        this.shoppedBooks = shoppedBooks;
    }

    public double getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(double numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public List<UserBookRatingDTO> getBooksAnRatingsDtos() {
        return booksAnRatingsDtos;
    }

    public void setBooksAnRatingsDtos(List<UserBookRatingDTO> booksAnRatingsDtos) {
        this.booksAnRatingsDtos = booksAnRatingsDtos;
    }
}
