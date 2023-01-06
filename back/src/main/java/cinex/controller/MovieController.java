package cinex.controller;

import cinex.controller.api.requests.CreateMovieRequest;
import cinex.model.Movie;
import cinex.security.SecurityHelper;
import cinex.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cinex.errors.AppException;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/first")
    public Movie first() throws AppException {
        var movies = movieService.findAll();
        if (movies.isEmpty())
            throw new AppException("brak filmów w bazie");
        return movies.get(0);
    }

    @GetMapping("/all")
    public List<Movie> all() {
        return movieService.findAll();
    }

    @GetMapping("/byID")
    public Movie byID(@RequestParam UUID id) throws AppException{
        var movie = movieService.findById(id);
        if(!movie.isPresent()) throw new AppException("Brak filmu w bazie");
    	return movie.get();
    }
    
    @GetMapping("/byTitle")
    public List<Movie> byTitle(@RequestParam String title){
    	return movieService.findByTitle(title);
    }
    
    @GetMapping("/upcoming")
    public List<Movie> upcoming() {
        return movieService.findUpcoming();
    }

    @GetMapping("/best")
    public List<Movie> best() {
        return movieService.findBest();
    }

    @GetMapping("/newest")
    public List<Movie> newest() {
        return movieService.findNewest();
    }

    @PostMapping("/create")
    public Movie create(@RequestBody CreateMovieRequest request) throws AppException {
        if (!Objects.requireNonNull(SecurityHelper.getLoggedUser()).isAdmin())
            throw new AppException("Nie masz uprawnień, aby dodać film");
        return movieService.create(request);
    }
}
