package cinex.repository;

import cinex.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    List<Movie> findByTitle(String title);

    List<Movie> findFirst10ByReleaseDateAfterOrderByReleaseDateAsc(java.util.Date releaseDate);

    List<Movie> findFirst10ByReleaseDateBeforeOrderByReleaseDateDesc(java.util.Date releaseDate);

    List<Movie> findAllByRatingIsNotNullOrderByRatingDesc();
}
