package cinex.service;

import cinex.controller.api.requests.CreateMovieRequest;
import cinex.model.Movie;
import cinex.errors.AppException;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    List<Movie> findUpcoming();

    List<Movie> findNewest();

    List<Movie> findBest();

    Movie create(CreateMovieRequest request) throws AppException;
}
