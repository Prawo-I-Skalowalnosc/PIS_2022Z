package cinex.controller.api.responses;

import cinex.model.Person;

import java.util.UUID;

public class PersonResponse {
    public UUID id;
    public String name;
    public String last_name;
    public String photo_url;

    public PersonResponse(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.last_name = person.getLast_name();
        this.photo_url = person.getPhoto_url();
    }
}
