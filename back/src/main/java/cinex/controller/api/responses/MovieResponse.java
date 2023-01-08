package cinex.controller.api.responses;

import cinex.model.Movie;
import cinex.model.MovieRating;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MovieResponse {
    public UUID id;
    public String title;
    public Float rating;
    public Float userRating;
    public String genre;
    public String country_of_origin;
    public Float budget;
    public String language;
    public Date releaseDate;
    public Integer length;
    public String poster_url;

    public MovieResponse(Movie movie){
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.rating = movie.getRating();
        this.userRating = countUserRating(movie.getRatings());
        this.genre = movie.getGenre();
        this.country_of_origin = movie.getCountry_of_origin();
        this.budget = movie.getBudget();
        this.language = movie.getLanguage();
        this.releaseDate = movie.getReleaseDate();
        this.length = movie.getLength();
        this.poster_url = movie.getPoster_url();
    }

    private Float countUserRating(List<MovieRating> ratings) {
        int value = 0;
        if (!ratings.isEmpty()){
            for (var rate : ratings) {
                value += rate.getValue();
            }
            return value / (float) ratings.size();
        }
        return (float) value;
    }
}
