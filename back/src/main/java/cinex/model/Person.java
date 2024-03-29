package cinex.model;

import cinex.controller.api.requests.CreatePeopleRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PEOPLE")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "person")
    private List<PersonInMovie> movies;

    private String name;
    private String last_name;
    private String photo_url;

    public Person(CreatePeopleRequest request) {
        movies = List.of();

        name = request.name;
        last_name = request.last_name;
        photo_url = request.photo_url;
    }
}
