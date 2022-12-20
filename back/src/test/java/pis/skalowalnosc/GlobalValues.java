package pis.skalowalnosc;

import pis.skalowalnosc.model.Movie;
import pis.skalowalnosc.model.User;

import java.util.Date;
import java.util.UUID;

public class GlobalValues {
    public static final String title = "testowyTytuł";
    public static final String username = "testowyUżytkownik";

    public static Movie getMovie() {
        return new Movie(new UUID(12, 34), null, null, title, "nobody",
                7.5f, "horror", "USA", 12345, "English", new Date(), 12);
    }

    public static User getUser() {
        return new User(new UUID(12, 34), null, username, '1');
    }
}
