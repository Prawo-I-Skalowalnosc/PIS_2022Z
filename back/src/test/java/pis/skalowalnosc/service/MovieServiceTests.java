package pis.skalowalnosc.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pis.skalowalnosc.errors.AppException;
import pis.skalowalnosc.model.Movie;
import pis.skalowalnosc.repository.MovieRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pis.skalowalnosc.GlobalTestValues.getMovieRequest;
import static pis.skalowalnosc.GlobalTestValues.title;

@SpringBootTest
public class MovieServiceTests {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @BeforeEach
    @AfterEach
    public void clean() {
        List<Movie> movies;
        if (!(movies = movieRepository.findByTitle(title)).isEmpty())
            movieRepository.deleteAll(movies);
    }

    @Test
    public void testCreate() throws AppException {
        var request = getMovieRequest();

        var addedMovie = movieService.create(request);

        assertEquals(1, movieRepository.findByTitle(addedMovie.getTitle()).size());

        assertEquals(request.getTitle(), addedMovie.getTitle());
        assertEquals(request.getLanguage(), addedMovie.getLanguage());
        assertEquals(request.getLength(), addedMovie.getLength());
    }

    @Test
    public void testCreateNoTitle() {
        var request = getMovieRequest();
        request.setTitle("");

        assertThrows(AppException.class, () -> movieService.create(request));
    }
}
