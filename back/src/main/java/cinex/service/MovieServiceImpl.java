package cinex.service;

import cinex.controller.api.requests.CreateMovieRequest;
import cinex.model.Movie;
import cinex.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cinex.errors.AppException;

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

    @Override
    public List<Movie> findUpcoming(){
        return movieRepository.findFirst10ByReleaseDateAfterOrderByReleaseDateAsc(new java.sql.Date(System.currentTimeMillis()));
    }

    @Override
    public List<Movie> findNewest() {
        return movieRepository.findFirst10ByReleaseDateBeforeOrderByReleaseDateDesc(new java.sql.Date(System.currentTimeMillis()));
    }

    @Override
    public List<Movie> findBest() {
        return movieRepository.findAllByRatingIsNotNullOrderByRatingsDesc();
    }


    @Override
    public Movie create(CreateMovieRequest request) throws AppException {
        if (request == null)
            throw new AppException("Nie podano danych nowego filmu");

        try {
            if (request.title.isBlank())
                throw new AppException("Podano pusty tytuł");

            var movie = new Movie(request);
            return movieRepository.save(movie);
        }
        catch (Exception e) {
            throw new AppException("Błąd podczas dodawania filmu");
        }
    }
}
