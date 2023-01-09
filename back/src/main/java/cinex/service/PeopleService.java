package cinex.service;

import cinex.controller.api.requests.CreatePeopleRequest;
import cinex.errors.AppException;
import cinex.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PeopleService {
    List<Person> findAll();

    Optional<Person> findById(UUID id);

    Person create(CreatePeopleRequest request) throws AppException;
}
