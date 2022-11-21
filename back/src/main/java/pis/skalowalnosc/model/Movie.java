package pis.skalowalnosc.model;

import java.util.Date;
import java.util.UUID;

public class Movie {

    private UUID movieId;
    private String title;
    private String author;
    private float rating;
    private String genre;
    private String country_of_origin;
    private float budget;
    private String language;
    private Date release_date;
    private int length;

    protected Movie() {
    }

    protected Movie(UUID movieId, String title, String author, float rating,
                 String genre, String country_of_origin, float budget,
                 String language, Date release_date, int length) {
        this.movieId = movieId;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.genre = genre;
        this.country_of_origin = country_of_origin;
        this.budget = budget;
        this.language = language;
        this.release_date = release_date;
        this.length = length;
    }

    public UUID getMovieId() {
        return movieId;
    }

    public void setMovieId(UUID movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry_of_origin() {
        return country_of_origin;
    }

    public void setCountry_of_origin(String country_of_origin) {
        this.country_of_origin = country_of_origin;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
