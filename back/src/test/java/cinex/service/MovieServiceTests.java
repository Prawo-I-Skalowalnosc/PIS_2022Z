package cinex.service;

import cinex.controller.api.requests.CreateMovieRequest;
import cinex.errors.AppException;
import cinex.model.Movie;
import cinex.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static cinex.GlobalTestValues.*;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testCreate_minimal() throws AppException {
        var request = new CreateMovieRequest(getMovie().getTitle(), 0f, "horror",
                null, 0f, null, null, 0, null);

        var addedMovie = movieService.create(request);

        assertEquals(1, movieRepository.findByTitle(addedMovie.getTitle()).size());

        assertEquals(request.title, addedMovie.getTitle());
        assertEquals(request.language, addedMovie.getLanguage());
        assertEquals(request.length, addedMovie.getLength());
        assertEquals(request.genre, addedMovie.getGenre());
    }

    @Test
    public void testCreateNoTitle() {
        var request = getMovieRequest();
        request.title = "";

        assertThrows(AppException.class, () -> movieService.create(request));
    }

    @Test
    public void testFindByTitle() {
        var absent = movieService.findByTitle(getMovie().getTitle());
        assertEquals(absent.size(), 0);

        movieRepository.save(getMovie());

        var movies = movieService.findByTitle(getMovie().getTitle());
        assertEquals(movies.size(), 1);
        assertEquals(movies.get(0).getTitle(), getMovie().getTitle());
        assertEquals(movies.get(0).getRating(), getMovie().getRating());
        assertEquals(movies.get(0).getLanguage(), getMovie().getLanguage());
    }

    @Test
    public void testFindUpcoming() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 15);

        var future1 = getMovie();
        future1.setReleaseDate(c.getTime());

        c.add(Calendar.DATE, 15);

        var future2 = getMovie();
        future2.setReleaseDate(c.getTime());

        movieRepository.save(future1);
        movieRepository.save(future2);

        var movies = movieService.findUpcoming().stream().filter(x -> Objects.equals(x.getTitle(), getMovie().getTitle())).toList();
        assertEquals(movies.size(), 2);
        assertEquals(movies.get(0).getTitle(), getMovie().getTitle());
        assertEquals(movies.get(0).getRating(), getMovie().getRating());
        assertEquals(movies.get(0).getLanguage(), getMovie().getLanguage());

        assertEquals(movies.get(1).getTitle(), getMovie().getTitle());
        assertEquals(movies.get(1).getRating(), getMovie().getRating());
        assertEquals(movies.get(1).getLanguage(), getMovie().getLanguage());

        assertTrue(movies.get(0).getReleaseDate().compareTo(movies.get(1).getReleaseDate()) < 0);
    }

    @Test
    public void testFindNewest() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -15);

        var future1 = getMovie();
        future1.setReleaseDate(c.getTime());

        c.add(Calendar.DATE, -15);

        var future2 = getMovie();
        future2.setReleaseDate(c.getTime());

        movieRepository.save(future1);
        movieRepository.save(future2);

        var movies = movieService.findNewest().stream().filter(x -> Objects.equals(x.getTitle(), getMovie().getTitle())).toList();
        assertEquals(movies.size(), 2);
        assertEquals(movies.get(0).getTitle(), getMovie().getTitle());
        assertEquals(movies.get(0).getRating(), getMovie().getRating());
        assertEquals(movies.get(0).getLanguage(), getMovie().getLanguage());

        assertEquals(movies.get(1).getTitle(), getMovie().getTitle());
        assertEquals(movies.get(1).getRating(), getMovie().getRating());
        assertEquals(movies.get(1).getLanguage(), getMovie().getLanguage());

        assertTrue(movies.get(0).getReleaseDate().compareTo(movies.get(1).getReleaseDate()) > 0);
    }

    @Test
    public void testFindBest_NotNull() {
        var better = getMovie();
        better.setRating(0.56f);

        var worse = getMovie();
        worse.setRating(0.16f);

        movieRepository.save(better);
        movieRepository.save(worse);

        var movies = movieService.findBest().stream().filter(x -> Objects.equals(x.getTitle(), getMovie().getTitle())).toList();
        assertEquals(movies.size(), 2);
        assertEquals(movies.get(0).getTitle(), getMovie().getTitle());
        assertEquals(movies.get(0).getRating(), 0.56f, 0.01f);
        assertEquals(movies.get(1).getTitle(), getMovie().getTitle());
        assertEquals(movies.get(1).getRating(), 0.16f, 0.01f);

        assertTrue(movies.get(0).getRating() > movies.get(1).getRating());
    }

    @Test
    public void testFindBest_SomeNull() {
        var noRating = getMovie();
        noRating.setRating(null);

        var better = getMovie();
        better.setRating(0.56f);

        var worse = getMovie();
        worse.setRating(0.16f);

        movieRepository.save(better);
        movieRepository.save(worse);
        movieRepository.save(noRating);

        var movies = movieService.findBest().stream().filter(x -> Objects.equals(x.getTitle(), getMovie().getTitle())).toList();
        assertEquals(movies.size(), 2);
        assertEquals(movies.get(0).getTitle(), getMovie().getTitle());
        assertEquals(movies.get(0).getRating(), 0.56f, 0.01f);
        assertEquals(movies.get(1).getTitle(), getMovie().getTitle());
        assertEquals(movies.get(1).getRating(), 0.16f, 0.01f);

        assertTrue(movies.get(0).getRating() > movies.get(1).getRating());
    }
}
