package cinex.repository;

import cinex.model.Movie;
import cinex.model.MovieRating;
import cinex.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static cinex.GlobalTestValues.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MovieRatingRepositoryTests {
    @Autowired
    private MovieRatingRepository movieRatingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;

    @AfterEach
    @BeforeEach
    public void clean() {
        Optional<User> user;
        if ((user = userRepository.findByUsername(username)).isPresent()) {
            var ratings = movieRatingRepository.findByRater(user.get());
            movieRatingRepository.deleteAll(ratings);
            userRepository.delete(user.get());
        }
        List<Movie> movies;
        if (!(movies = movieRepository.findByTitle(title)).isEmpty())
            movieRepository.deleteAll(movies);

    }
    private static void compareMovieRatings(MovieRating movieRating, MovieRating DBmovieRating) {
        assertEquals(movieRating.getMovie().getId(), DBmovieRating.getMovie().getId());
        assertEquals(movieRating.getRater().getId(), DBmovieRating.getRater().getId());
        assertEquals(movieRating.getValue(), DBmovieRating.getValue());
    }

    @Test
    public void testFindByRaterAndMovie(){
        var movie = getMovie();
        var rater = getUser();
        movie = movieRepository.save(movie);
        rater = userRepository.save(rater);

        var rating = getMovieRating(movie, rater, 3);
        movieRatingRepository.save(rating);

        var ratingDB = movieRatingRepository.findByRaterAndMovie(rater, movie);
        assertTrue(ratingDB.isPresent());
        ratingDB.ifPresent(movieRating -> compareMovieRatings(rating, movieRating));
    }
}
