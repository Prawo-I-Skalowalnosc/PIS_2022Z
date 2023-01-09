package cinex.controller;

import cinex.controller.api.requests.CreatePersonRequest;
import cinex.controller.api.responses.PersonResponse;
import cinex.errors.AppException;
import cinex.security.SecurityHelper;
import cinex.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/people")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/all")
    public List<PersonResponse> all() {
        return personService.findAll().stream()
                .map(PersonResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public PersonResponse create(@RequestBody CreatePersonRequest request) throws AppException {
        if (!Objects.requireNonNull(SecurityHelper.getLoggedUser()).isAdmin())
            throw new AppException("Nie masz uprawnień, aby dodać film");
        return new PersonResponse(personService.create(request));
    }
}
