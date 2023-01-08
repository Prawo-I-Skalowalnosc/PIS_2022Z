package cinex;

import cinex.controller.api.requests.CreateMovieRequest;
import cinex.model.Movie;
import cinex.model.MovieRating;
import cinex.model.User;
import cinex.service.UserService;
import net.bytebuddy.utility.RandomString;

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

    public static String getToken() {
        return UserService.generateToken(getUser());
    }

    public static User getUser() {
        return new User(UUID.randomUUID(), List.of(), username,
                'A', RandomString.make(32) + "@abcdef.pl", new Date(), "hasełko1234", "abb16f9e6a956ac207c727ea39c23fff");
    }

    public static MovieRating getMovieRating(Movie m, User u, Integer value){
        return new MovieRating(m, u, value);
    }
}
