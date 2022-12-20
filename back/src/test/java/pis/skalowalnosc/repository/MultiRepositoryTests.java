package pis.skalowalnosc.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pis.skalowalnosc.model.Movie;
import pis.skalowalnosc.model.MovieRating;
import pis.skalowalnosc.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pis.skalowalnosc.GlobalValues.*;

@SpringBootTest
public class MultiRepositoryTests {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @AfterEach
    public void clean() {
        List<Movie> movies;
        if (!(movies = movieRepository.findByTitle(title)).isEmpty())
            movieRepository.deleteAll(movies);
        List<User> users;
        if (!(users = userRepository.findByUsername(username)).isEmpty())
            userRepository.deleteAll(users);
    }

    @Test
    public void testAddMovieRating() {
        var movie = getMovie();
        var user = getUser();

        var rating = new MovieRating(user, movie, 5);
        user.getRatings().add(rating);

        var DBMovie = movieRepository.save(movie);
        var DBUser = userRepository.save(user);

        assertEquals(1, DBUser.getRatings().size());
        assertEquals(1, DBMovie.getRatings().size());

        var DBRating = DBUser.getRatings().get(0);

        assertEquals(5, DBRating.getValue());
        assertEquals(DBMovie, DBRating.getMovie());
        assertEquals(DBUser, DBRating.getRater());
    }
}
