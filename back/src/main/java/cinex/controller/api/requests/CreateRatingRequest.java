package cinex.controller.api.requests;

import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
public class CreateRatingRequest {
    public UUID raterId;
    public UUID movieId;
    public Integer rating;
}
