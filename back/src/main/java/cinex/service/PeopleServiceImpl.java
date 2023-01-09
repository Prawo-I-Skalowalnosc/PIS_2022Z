package cinex.service;

import cinex.controller.api.requests.CreatePeopleRequest;
import cinex.errors.AppException;
import cinex.model.Person;
import cinex.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Override
    public Optional<Person> findById(UUID id){
        return peopleRepository.findById(id);
    }

    @Override
    public Person create(CreatePeopleRequest request) throws AppException {
        if (request == null)
            throw new AppException("Nie podano danych nowej osoby");

        try {
            if (request.name == null || request.name.isBlank())
                throw new AppException("Podano puste imię");

            if (request.last_name == null || request.last_name.isBlank())
                throw new AppException("Podano puste nazwisko");

            var person = new Person(request);
            return peopleRepository.save(person);
        }
        catch (Exception e) {
            throw new AppException("Błąd podczas dodawania osoby");
        }
    }
}
