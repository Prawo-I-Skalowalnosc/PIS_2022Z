package cinex.service;

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
    public MovieRating updateOrCreateRating(MovieRating movie_rating){
        return movieRatingRepository.save(movie_rating);
    }

    @Override
    public MovieRating create(Movie movie, User user, Integer rating){
        return movieRatingRepository.save(new MovieRating(movie, user, rating));
    }
}
