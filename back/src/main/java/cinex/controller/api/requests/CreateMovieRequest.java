package cinex.controller.api.requests;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class CreateMovieRequest {
    public String title;
    public float rating;
    public String genre;
    public String country_of_origin;
    public float budget;
    public String language;
    public Date releaseDate;
    public int length;
    public String poster_url;
}
