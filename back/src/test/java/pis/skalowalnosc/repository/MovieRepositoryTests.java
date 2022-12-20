package pis.skalowalnosc.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pis.skalowalnosc.model.Movie;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pis.skalowalnosc.GlobalValues.getMovie;
import static pis.skalowalnosc.GlobalValues.title;

@SpringBootTest
public class MovieRepositoryTests {
    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    @AfterEach
    public void clean() {
        List<Movie> movies;
        if (!(movies = movieRepository.findByTitle(title)).isEmpty())
            movieRepository.deleteAll(movies);
    }

    private static void compareMovies(Movie movie, Movie DBMovie) {
        assertEquals(movie.getTitle(), DBMovie.getTitle());
        assertEquals(movie.getAuthor(), DBMovie.getAuthor());
        assertEquals(movie.getRating(), DBMovie.getRating());
        assertEquals(movie.getRelease_date(), DBMovie.getRelease_date());
        assertTrue(DBMovie.getPeople().isEmpty());
        assertTrue(DBMovie.getRatings().isEmpty());
        assertEquals(movie.getLanguage(), DBMovie.getLanguage());
        assertEquals(movie.getLength(), DBMovie.getLength());
    }

    @Test
    public void testAdd_Find() {
        var movie = getMovie();
        var movies = movieRepository.findByTitle(movie.getTitle());

        assertFalse(movies.isEmpty());
        assertEquals(1, movies.size());
        assertNotNull(movies.get(0));

        var DBMovie = movies.get(0);

        compareMovies(movie, DBMovie);
    }

    @Test
    public void testAdd_Get() {
        var movie = getMovie();
        var DBMovie = movieRepository.save(movie);

        compareMovies(movie, DBMovie);
    }
}
