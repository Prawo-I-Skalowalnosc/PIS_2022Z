package cinex.controller;

import cinex.controller.api.responses.PersonResponse;
import cinex.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
}
