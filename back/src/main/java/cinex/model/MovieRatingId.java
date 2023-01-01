package cinex.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class MovieRatingId implements Serializable {
    private UUID rater;
    private UUID movie;
}
