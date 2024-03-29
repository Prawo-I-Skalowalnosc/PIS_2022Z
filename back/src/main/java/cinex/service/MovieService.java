package cinex.service;

import cinex.controller.api.requests.CreateMovieRequest;
import cinex.model.Movie;
import cinex.errors.AppException;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAll();
    
    List<Movie> findByTitle(String title);
    
    Optional<Movie> findById(UUID id);
    
    List<Movie> findUpcoming();

    List<Movie> findNewest();

    List<Movie> findBest();

    Movie create(CreateMovieRequest request) throws AppException;
}
