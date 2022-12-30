package pis.skalowalnosc;

import net.bytebuddy.utility.RandomString;
import pis.skalowalnosc.controller.api.requests.CreateMovieRequest;
import pis.skalowalnosc.model.Movie;
import pis.skalowalnosc.model.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GlobalTestValues {
    public static final String title = "testowyTytuł";
    public static final String username = "testowyUżytkownik";
    public static final String url = "http://localhost:";

    public static Movie getMovie() {
        return new Movie(UUID.randomUUID(), List.of(), List.of(), title,
                0.7f, "horror", "USA", 123f, "English",
                new Date(), 12, "testowy.url");
    }

    public static CreateMovieRequest getMovieRequest() {
        return new CreateMovieRequest(title, 0.6f, "sci-fi", "Canada", 321f,
                "Francuski",  new Date(), 85, "testowy.url.pl");
    }

    public static User getUser() {
        return new User(UUID.randomUUID(), List.of(), username,
                '1', RandomString.make(32) + "@abcdef.pl", new Date(), RandomString.make(32), RandomString.make(32));
    }
}
