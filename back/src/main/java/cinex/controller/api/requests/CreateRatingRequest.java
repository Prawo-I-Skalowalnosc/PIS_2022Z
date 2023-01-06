package cinex.controller.api.requests;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class CreateRatingRequest {
    public UUID movieId;
    public Integer rating;
}
