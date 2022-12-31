package pis.skalowalnosc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pis.skalowalnosc.model.Movie;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    List<Movie> findByTitle(String title);

    List<Movie> findFirst10ByReleaseDateAfterOrderByReleaseDateAsc(java.util.Date releaseDate);

    List<Movie> findFirst10ByReleaseDateBeforeOrderByReleaseDateDesc(java.util.Date releaseDate);

    List<Movie> findAllByRatingIsNotNullOrderByRatingsDesc();
}
