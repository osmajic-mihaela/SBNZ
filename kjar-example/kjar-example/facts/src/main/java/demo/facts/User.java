package demo.facts;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    private List<ShoppingDto> shoppedBooks;
    private double numberOfRatings;
    private List<UserBookRatingDTO> booksAnRatingsDtos;

    private List<BookCategory> interestedGenres;
    private List<String> interestedWriters;

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
        this.shoppedBooks = new ArrayList<ShoppingDto>();
        this.numberOfRatings = 0.0;
        this.booksAnRatingsDtos = new ArrayList<>();
        this.interestedGenres = new ArrayList<>();
        this.interestedWriters = new ArrayList<>();
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
        this.shoppedBooks = new ArrayList<ShoppingDto>();
        this.numberOfRatings = numberOfRatings;
        this.booksAnRatingsDtos = dtos;
        this.interestedGenres = new ArrayList<>();
        this.interestedWriters = new ArrayList<>();
    }

    //Zainteresovani zanrovi i autori
    public User(String email, String password, String name, String surname, String phoneNum, Role role, ArrayList<BookCategory> favoriteGenres, double numberOfRatings, ArrayList<UserBookRatingDTO> dtos,ArrayList<BookCategory> interestedGenres,ArrayList<String> interestedWriters, ArrayList<ShoppingDto> shoppedBooks) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNum = phoneNum;
        this.role=role;
        this.isNew = true;
        this.favoriteGenre = favoriteGenres;
        this.likedBook = new ArrayList<Book>();
        this.shoppedBooks = shoppedBooks;
        this.numberOfRatings = numberOfRatings;
        this.booksAnRatingsDtos = dtos;
        this.interestedGenres = interestedGenres;
        this.interestedWriters = interestedWriters;
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

    public List<ShoppingDto> getShoppedBooks() {
        return shoppedBooks;
    }

    public void setShoppedBooks(List<ShoppingDto> shoppedBooks) {
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

    public List<BookCategory> getInterestedGenres() {
        return interestedGenres;
    }

    public void setInterestedGenres(List<BookCategory> interestedGenres) {
        this.interestedGenres = interestedGenres;
    }

    public List<String> getInterestedWriters() {
        return interestedWriters;
    }

    public void setInterestedWriters(List<String> interestedWriters) {
        this.interestedWriters = interestedWriters;
    }

    public ArrayList<Book> getShoppedBoooks(){
        ArrayList<Book> t = new ArrayList<>();
        for(ShoppingDto d:this.getShoppedBooks()){

            if(d.date.after(new Date(System.currentTimeMillis() - (6L * 30 * 24 * 60 * 60 * 1000)))) t.add(d.book);
        }

        return t;
    }

}
