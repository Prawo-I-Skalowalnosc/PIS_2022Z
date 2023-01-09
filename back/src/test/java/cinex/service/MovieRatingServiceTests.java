package cinex.service;

import cinex.errors.AppException;
import cinex.model.Movie;
import cinex.model.MovieRating;
import cinex.model.User;
import cinex.repository.MovieRatingRepository;
import cinex.repository.MovieRepository;
import cinex.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static cinex.GlobalTestValues.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieRatingServiceTests {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRatingRepository movieRatingRepository;
    @Autowired
    private MovieRatingService movieRatingService;

    @BeforeEach
    @AfterEach
    public void clean() {
        List<MovieRating> ratings;
        if (!(ratings = movieRatingRepository.findByMovieTitle(title)).isEmpty())
            movieRatingRepository.deleteAll(ratings);
        List<Movie> movies;
        if (!(movies = movieRepository.findByTitle(title)).isEmpty())
            movieRepository.deleteAll(movies);
        Optional<User> user;
        if ((user = userRepository.findByUsername(username)).isPresent())
            userRepository.delete(user.get());
    }

    private MovieRating addRating() {
        var user = getUser();
        var movie = getMovie();
        user = userRepository.save(user);
        movie = movieRepository.save(movie);

        var rating = getMovieRating(movie, user, 3);
        return movieRatingRepository.save(rating);
    }

    @Test
    public void testGetRating() {
        var foundRating = movieRatingService.getRating(getUser(), getMovie());
        assertTrue(foundRating.isEmpty());

        var rating = addRating();

        foundRating = movieRatingService.getRating(rating.getRater(), rating.getMovie());
        assertTrue(foundRating.isPresent());
        assertEquals(foundRating.get().getValue(), rating.getValue());
        assertEquals(foundRating.get().getRater().getId(), rating.getRater().getId());
        assertEquals(foundRating.get().getMovie().getId(), rating.getMovie().getId());
    }

    @Test
    public void testCreateOrUpdateRating_Create() throws AppException {
        var user = getUser();
        var movie = getMovie();
        user = userRepository.save(user);
        movie = movieRepository.save(movie);

        var rating = getMovieRating(movie, user, 6);

        var response = movieRatingService.updateOrCreateRating(rating);
        assertTrue(response.isNew);
        assertEquals(response.rating, rating.getValue());
        assertEquals(response.movieId, rating.getMovie().getId());
    }

    @Test
    public void testCreateOrUpdateRating_Update() throws AppException {
        var user = getUser();
        var movie = getMovie();
        user = userRepository.save(user);
        movie = movieRepository.save(movie);

        var rating = getMovieRating(movie, user, 6);

        var response = movieRatingService.updateOrCreateRating(rating);
        assertTrue(response.isNew);
        assertEquals(response.rating, rating.getValue());
        assertEquals(response.movieId, rating.getMovie().getId());

        rating.setValue(3);

        var newResponse = movieRatingService.updateOrCreateRating(rating);
        assertFalse(newResponse.isNew);
        assertEquals(newResponse.rating, rating.getValue());
        assertEquals(newResponse.movieId, rating.getMovie().getId());

        assertNotEquals(newResponse.rating, response.rating);
        assertEquals(newResponse.movieId, response.movieId);
    }

    @Test
    public void testCreateOrUpdateRating_BeforeRelease() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 15);

        var user = getUser();
        var movie = getMovie();
        movie.setReleaseDate(c.getTime());
        user = userRepository.save(user);
        movie = movieRepository.save(movie);

        var rating = getMovieRating(movie, user, 6);

        assertThrows(AppException.class, () -> movieRatingService.updateOrCreateRating(rating));
    }

    @Test
    public void testCreate_BeforeRelease() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 15);

        var user = getUser();
        var movie = getMovie();
        movie.setReleaseDate(c.getTime());

        userRepository.save(user);
        movieRepository.save(movie);

        assertThrows(AppException.class, () -> movieRatingService.create(movie, user, 5));
    }
}
