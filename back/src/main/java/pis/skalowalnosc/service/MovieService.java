package pis.skalowalnosc.service;

import pis.skalowalnosc.controller.api.CreateMovieRequest;
import pis.skalowalnosc.errors.AppException;
import pis.skalowalnosc.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    Movie create(CreateMovieRequest request) throws AppException;
}
