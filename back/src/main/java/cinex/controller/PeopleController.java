package cinex.controller;

import cinex.controller.api.requests.CreatePeopleRequest;
import cinex.controller.api.responses.PeopleResponse;
import cinex.errors.AppException;
import cinex.security.UserRoles;
import cinex.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static cinex.security.SecurityHelper.requireRoles;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    @GetMapping("/all")
    public List<PeopleResponse> all() {
        return peopleService.findAll().stream()
                .map(PeopleResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/byID")
    public PeopleResponse byID(@RequestParam UUID id) throws AppException{
        var person = peopleService.findById(id);
        if(person.isEmpty()) throw new AppException("Brak osoby w bazie");
        return new PeopleResponse(person.get());
    }

    @PostMapping("/create")
    public PeopleResponse create(@RequestBody CreatePeopleRequest request) throws AppException {
        if (!requireRoles(List.of(UserRoles.ADMIN, UserRoles.MODERATOR)))
            throw new AppException("Nie masz uprawnień, aby dodać osobę");
        return new PeopleResponse(peopleService.create(request));
    }
}
