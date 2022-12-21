package pis.skalowalnosc;

import net.bytebuddy.utility.RandomString;
import pis.skalowalnosc.model.Movie;
import pis.skalowalnosc.model.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GlobalValues {
    public static final String title = "testowyTytuł";
    public static final String username = "testowyUżytkownik";
    public static final String url = "http://localhost:";

    public static Movie getMovie() {
        return new Movie(UUID.randomUUID(), List.of(), List.of(), title,
                7, "horror", "USA", 123, "English",
                new Date(), 12, "testowy.url");
    }

    public static User getUser() {
        return new User(UUID.randomUUID(), List.of(), username,
                '1', "test@gmail.com", new Date(), RandomString.make(32), RandomString.make(32));
    }
}
