package cinex.controller;

import cinex.controller.api.requests.CreateRatingRequest;
import cinex.controller.api.responses.MovieRatingResponse;
import cinex.model.Movie;
import cinex.security.SecurityHelper;
import cinex.service.MovieService;
import cinex.service.UserService;
import cinex.service.MovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/movieRatings")
public class MovieRatingController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieRatingService movieRatingService;

    @PutMapping("/addRating")
    public MovieRatingResponse addRating(@RequestBody CreateRatingRequest request){

        var opt_movie = movieService.findById(request.movieId);
        Movie movie;
        if (opt_movie.isPresent()) {
            movie = opt_movie.get();
        } else {
            return new MovieRatingResponse(false, "There is no such film.");
        }

        var user = SecurityHelper.getLoggedUser();
        if (user == null){
            return new MovieRatingResponse(false, "You are not logged in");
        }

        var opt_rating = movieRatingService.getRating(user, movie);
        if (opt_rating.isPresent()) {
            var rating = opt_rating.get();
            rating.setValue(request.rating);
            movieRatingService.create(movie, user, request.rating);
            return new MovieRatingResponse(true, "Rating edition");
        } else {
            movieRatingService.create(movie, user, request.rating);
            return new MovieRatingResponse(true, "New rating");
        }

    }

}
