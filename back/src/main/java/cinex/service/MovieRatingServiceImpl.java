package cinex.service;

import cinex.controller.api.responses.RatingResponse;
import cinex.model.Movie;
import cinex.model.MovieRating;
import cinex.model.User;
import cinex.repository.MovieRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieRatingServiceImpl implements MovieRatingService{
    @Autowired
    private MovieRatingRepository movieRatingRepository;

    @Override
    public Optional<MovieRating> getRating(User rater, Movie movie) {
        return movieRatingRepository.findByRaterAndMovie(rater, movie);
    }

    @Override
    public RatingResponse updateOrCreateRating(MovieRating movie_rating){
        var isNew = getRating(movie_rating.getRater(), movie_rating.getMovie()).isEmpty();
        return new RatingResponse(movieRatingRepository.save(movie_rating), isNew);
    }

    @Override
    public MovieRating create(Movie movie, User user, Integer rating){
        return movieRatingRepository.save(new MovieRating(movie, user, rating));
    }
}
