package cinex.repository;

import cinex.model.Movie;
import cinex.model.MovieRating;
import cinex.model.User;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static cinex.GlobalTestValues.*;
import static org.junit.jupiter.api.Assertions.*;

public class MovieRatingRepositoryTests {
    @Autowired
    private MovieRatingRepository movieRatingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;

    @AfterEach
    public void clean() {
        Movie movie = getMovie();
        User user = getUser();
        Optional<MovieRating> movieRatings;
        movieRatings = movieRatingRepository.findByRaterAndMovie(user, movie);
        movieRatings.ifPresent(movieRating -> movieRatingRepository.delete(movieRating));
        movieRepository.delete(movie);
        userRepository.delete(user);
    }
    private static void compareMovieRatings(MovieRating movieRating, MovieRating DBmovieRating) {
        assertEquals(movieRating.getMovie(), DBmovieRating.getMovie());
        assertEquals(movieRating.getRater(), DBmovieRating.getRater());
        assertEquals(movieRating.getValue(), DBmovieRating.getValue());
        assertEquals(movieRating.getDate_rated(), DBmovieRating.getDate_rated());
    }

    @Test
    public void testFindByRaterAndMovie(){
        var movie = getMovie();
        var rater = getUser();
        var rating = getMovieRating(movie, rater, 3);
        movieRepository.save(movie);
        movieRatingRepository.save(rating);
        userRepository.save(rater);

        var ratingDB = movieRatingRepository.findByRaterAndMovie(rater, movie);
        ratingDB.ifPresent(movieRating -> compareMovieRatings(rating, movieRating));

    }
}
