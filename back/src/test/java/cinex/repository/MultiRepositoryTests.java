package cinex.repository;

import cinex.model.Movie;
import cinex.model.MovieRating;
import cinex.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static cinex.GlobalTestValues.*;

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
        Optional<User> user;
        if ((user = userRepository.findByUsername(username)).isPresent())
            userRepository.delete(user.get());
    }

    @Test
    public void testAddMovieRating() {
        var movie = getMovie();
        var user = getUser();

        var DBMovie = movieRepository.save(movie);
        var DBUser = userRepository.save(user);

        var rating = new MovieRating(DBUser, DBMovie, 5, new Date());

        DBMovie.setRatings(List.of(rating));
        DBMovie = movieRepository.save(DBMovie);

        DBUser.setRatings(List.of(rating));
        DBUser = userRepository.save(DBUser);

        assertEquals(1, DBUser.getRatings().size());
        Assertions.assertEquals(1, DBMovie.getRatings().size());

        var DBRating = DBUser.getRatings().get(0);

        assertEquals(5, DBRating.getValue());
        Assertions.assertEquals(DBMovie.getId(), DBRating.getMovie().getId());
        assertEquals(DBUser.getId(), DBRating.getRater().getId());
    }
}
