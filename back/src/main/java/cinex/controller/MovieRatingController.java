package cinex.controller;

import cinex.controller.api.requests.CreateRatingRequest;
import cinex.controller.api.responses.RatingResponse;
import cinex.errors.AppException;
import cinex.model.MovieRating;
import cinex.security.SecurityHelper;
import cinex.service.MovieService;
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
    private MovieRatingService movieRatingService;

    @PutMapping("/addRating")
    public RatingResponse addRating(@RequestBody CreateRatingRequest request) throws AppException {
        var opt_movie = movieService.findById(request.movieId);

        if (opt_movie.isEmpty())
            throw new AppException("Nie ma takiego filmu");

        var movie = opt_movie.get();

        var user = SecurityHelper.getLoggedUser();
        if (user == null)
            throw new AppException("Nie jesteś zalogowany");

        return movieRatingService.updateOrCreateRating(new MovieRating(
            movie,
            user,
            request.rating)
        );
    }
}
