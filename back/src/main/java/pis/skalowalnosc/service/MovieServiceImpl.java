package pis.skalowalnosc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis.skalowalnosc.model.Movie;
import pis.skalowalnosc.repository.MovieRepository;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    // Impl po nazwie gwarantuje że spring sam to ogarnie, nie trzeba dodawać gettera w Config
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}
