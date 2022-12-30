package pis.skalowalnosc.service;

import pis.skalowalnosc.controller.api.requests.CreateMovieRequest;
import pis.skalowalnosc.errors.AppException;
import pis.skalowalnosc.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    List<Movie> findUpcoming();

    List<Movie> findNewest();

    List<Movie> findBest();

    Movie create(CreateMovieRequest request) throws AppException;


}
