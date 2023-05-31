package demo.facts;

import java.util.List;

public class UserBookRatingDTO {
    public String userEmail;
    public User user;
    public int rating;

    public String bookName;
    public Book book;

    public boolean Oj(List<UserBookRatingDTO> ratings) {
        for (UserBookRatingDTO u : ratings) {
            if ( this.userEmail == u.userEmail && (Math.abs(this.rating - u.rating) == 0 || Math.abs(this.rating - u.rating) == 1 )) {
                return true;
            }

        }

        return false;
    }

    public boolean OjOj(List<UserBookRatingDTO> ratings) {
        for (UserBookRatingDTO u : ratings) {
            if (this.userEmail == u.userEmail) {
                return true;
            }
        }
        return false;
    }
}
