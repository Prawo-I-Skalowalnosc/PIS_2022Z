package cinex.service;

import cinex.controller.api.responses.RatingResponse;
import cinex.model.Movie;
import cinex.model.MovieRating;
import cinex.model.User;

import java.util.Optional;

public interface MovieRatingService {
    Optional<MovieRating> getRating(User rater, Movie movie);
    MovieRating create(Movie movie, User user, Integer rating);
    RatingResponse updateOrCreateRating(MovieRating movie_rating);
}
