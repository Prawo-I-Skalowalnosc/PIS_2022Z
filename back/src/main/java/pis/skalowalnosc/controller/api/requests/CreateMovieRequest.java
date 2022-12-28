package pis.skalowalnosc.controller.api.requests;

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
    public Date release_date;
    public int length;
    public String poster_url;
}
