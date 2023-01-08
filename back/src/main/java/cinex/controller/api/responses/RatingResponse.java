package cinex.controller.api.responses;

import cinex.model.MovieRating;

import java.util.UUID;

public class RatingResponse {
    public UUID movieId;
    public boolean isNew;
    public int rating;

    public RatingResponse(MovieRating rating, boolean isNew) {
        movieId = rating.getMovie().getId();
        this.rating = rating.getValue();
        this.isNew = isNew;
    }
}
