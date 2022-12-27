package pis.skalowalnosc.controller.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CreateMovieRequest {
    private String title;
    private float rating;
    private String genre;
    private String country_of_origin;
    private float budget;
    private String language;
    private Date release_date;
    private int length;
    private String poster_url;
}
