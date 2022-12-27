package pis.skalowalnosc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<MovieRating> ratings;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<PersonInMovie> people;

    // jeśli pole ma inną nazwę niż w DB, to należy dać @Column(name="nazwa_kolumny") i opcjonalnie inne parametry typu unikalność
    private String title;
    private float rating; // 0 <= rating <= 1
    private String genre;
    private String country_of_origin;
    private float budget;
    private String language;
    private Date release_date;
    private int length;
    private String poster_url;
}
