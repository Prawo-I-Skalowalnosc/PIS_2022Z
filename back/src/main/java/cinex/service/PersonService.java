package cinex.service;

import cinex.controller.api.requests.CreatePersonRequest;
import cinex.errors.AppException;
import cinex.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {
    List<Person> findAll();

    Optional<Person> findById(UUID id);

    Person create(CreatePersonRequest request) throws AppException;
}
