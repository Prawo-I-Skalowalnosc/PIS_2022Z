package cinex.repository;

import cinex.model.Movie;
import cinex.model.MovieRating;
import cinex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRatingRepository extends JpaRepository<MovieRating, UUID> {
    Optional<MovieRating> findByRaterAndMovie(User rater, Movie movie);
    List<MovieRating> findByRater(User rater);
    List<MovieRating> findByMovieTitle(String movie_title);
}
