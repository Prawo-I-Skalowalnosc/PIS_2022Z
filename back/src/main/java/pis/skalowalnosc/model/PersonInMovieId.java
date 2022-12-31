package pis.skalowalnosc.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class PersonInMovieId implements Serializable {
    private UUID movie;
    private UUID person;
}
