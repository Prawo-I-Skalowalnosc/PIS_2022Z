package cinex.repository;

import cinex.model.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static cinex.GlobalTestValues.getMovie;
import static cinex.GlobalTestValues.title;

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
        assertEquals(movie.getCountry_of_origin(), DBMovie.getCountry_of_origin());
        assertEquals(movie.getRating(), DBMovie.getRating());
        assertTrue(DBMovie.getPeople().isEmpty());
        assertTrue(DBMovie.getRatings().isEmpty());
        assertEquals(movie.getLanguage(), DBMovie.getLanguage());
        assertEquals(movie.getLength(), DBMovie.getLength());
    }

    @Test
    public void testAdd_Find() {
        var movie = getMovie();
        movieRepository.save(movie);
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

    @Test
    public void testDelete() {
        var movie = getMovie();
        var DBMovie = movieRepository.save(movie);

        assertTrue(movieRepository.findById(DBMovie.getId()).isPresent());

        movieRepository.delete(DBMovie);

        assertTrue(movieRepository.findById(DBMovie.getId()).isEmpty());
    }
}
