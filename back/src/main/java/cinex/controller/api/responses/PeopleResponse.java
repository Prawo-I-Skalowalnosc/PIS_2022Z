package cinex.controller.api.responses;

import cinex.model.Movie;
import cinex.model.MovieRating;
import cinex.model.Person;
import cinex.model.PersonInMovie;
import cinex.security.UserRoles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class PeopleResponse {
    public List<String> directors;
    public List<String> screenwriters;
    public List<String> actors;


    public PeopleResponse(Movie movie) {
        this.directors = new ArrayList<>();
        this.screenwriters = new ArrayList<>();
        this.actors = new ArrayList<>();

        for (PersonInMovie person : movie.getPeople()) {
            var role = person.getRole();
            if (role.equalsIgnoreCase("DIRECTOR"))
                this.directors.add(getFullName(person));
            else if (role.equalsIgnoreCase("SCREENWRITER"))
                this.screenwriters.add(getFullName(person));
            else
                this.actors.add(getFullName(person));
        }
    }

    String getFullName(PersonInMovie person) {
        return (person.getPerson().getName() + " " + person.getPerson().getLast_name()).trim();
    }
}
