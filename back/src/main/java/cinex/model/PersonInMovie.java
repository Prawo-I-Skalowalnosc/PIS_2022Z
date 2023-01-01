package cinex.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(PersonInMovieId.class)
@Table(name = "peopleinmovie")
public class PersonInMovie {
    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
    @Id
    @ManyToOne
    @JoinColumn(name = "people_id", referencedColumnName = "id")
    private Person person;

    private String role;
}
