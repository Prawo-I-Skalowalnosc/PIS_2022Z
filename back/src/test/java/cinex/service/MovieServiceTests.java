package cinex.service;

import cinex.model.Movie;
import cinex.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import cinex.errors.AppException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static cinex.GlobalTestValues.getMovieRequest;
import static cinex.GlobalTestValues.title;

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

        assertEquals(request.title, addedMovie.getTitle());
        assertEquals(request.language, addedMovie.getLanguage());
        assertEquals(request.length, addedMovie.getLength());
    }

    @Test
    public void testCreateNoTitle() {
        var request = getMovieRequest();
        request.title = "";

        assertThrows(AppException.class, () -> movieService.create(request));
    }
}
