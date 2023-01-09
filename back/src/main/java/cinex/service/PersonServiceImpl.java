package cinex.service;

import cinex.controller.api.requests.CreatePersonRequest;
import cinex.errors.AppException;
import cinex.model.Person;
import cinex.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> findById(UUID id){
        return personRepository.findById(id);
    }

    @Override
    public Person create(CreatePersonRequest request) throws AppException {
        if (request == null)
            throw new AppException("Nie podano danych nowej osoby");

        try {
            if (request.name == null || request.name.isBlank())
                throw new AppException("Podano puste imię");

            if (request.last_name == null || request.last_name.isBlank())
                throw new AppException("Podano puste nazwisko");

            var person = new Person(request);
            return personRepository.save(person);
        }
        catch (Exception e) {
            throw new AppException("Błąd podczas dodawania osoby");
        }
    }
}
