package cinex.controller;

import cinex.controller.api.requests.CreateMovieRequest;
import cinex.controller.api.responses.MovieResponse;
import cinex.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cinex.errors.AppException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/first")
    public MovieResponse first() throws AppException {
        var movies = movieService.findAll();
        if (movies.isEmpty())
            throw new AppException("brak filmów w bazie");
        return new MovieResponse(movies.get(0));
    }

    @GetMapping("/all")
    public List<MovieResponse> all() {
        return movieService.findAll().stream()
                .map(MovieResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/byID")
    public MovieResponse byID(@RequestParam UUID id) throws AppException{
        var movie = movieService.findById(id);
        if(!movie.isPresent()) throw new AppException("Brak filmu w bazie");
    	return new MovieResponse(movie.get());
    }
    
    @GetMapping("/byTitle")
    public List<MovieResponse> byTitle(@RequestParam String title){
        return movieService.findByTitle(title).stream()
                .map(MovieResponse::new)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/upcoming")
    public List<MovieResponse> upcoming() {
        return  movieService.findUpcoming().stream()
                .map(MovieResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/best")
    public List<MovieResponse> best() {
        return movieService.findBest().stream()
                .map(MovieResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/newest")
    public List<MovieResponse> newest() {
        return movieService.findNewest().stream()
                .map(MovieResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public MovieResponse create(@RequestBody CreateMovieRequest request) throws AppException {
        return new MovieResponse(movieService.create(request));
    }
}
