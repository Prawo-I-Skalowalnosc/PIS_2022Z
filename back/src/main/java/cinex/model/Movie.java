package cinex.model;

import cinex.controller.api.requests.CreateMovieRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<MovieRating> ratings;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<PersonInMovie> people;
    private String title;
    private Float rating; // 0 <= rating <= 1
    private String genre;
    private String country_of_origin;
    private Float budget;
    private String language;

    @Column(name="release_date")
    private Date releaseDate;
    private Integer length;
    private String poster_url;

    public Movie(CreateMovieRequest request) {
        ratings = List.of();
        people = List.of();

        title = request.title;
        rating = request.rating;
        genre = request.genre;
        country_of_origin = request.country_of_origin;
        budget = request.budget;
        language = request.language;
        releaseDate = request.releaseDate != null ? request.releaseDate : new Date();
        length = request.length;
        poster_url = request.poster_url;
    }
}
