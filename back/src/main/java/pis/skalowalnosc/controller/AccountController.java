package pis.skalowalnosc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pis.skalowalnosc.errors.AppException;
import pis.skalowalnosc.controller.api.requests.LoginRequest;
import pis.skalowalnosc.controller.api.requests.RegisterRequest;
import pis.skalowalnosc.controller.api.responses.LoginResponse;
import pis.skalowalnosc.controller.api.responses.RegisterResponse;
import pis.skalowalnosc.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService accountService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest login) throws AppException {
        if (accountService.login(login))
            return new LoginResponse(true, "this is supposed to be token", "Zalogowano");
        return new LoginResponse(false, null, "Niepoprawne hasło");
        // pis narazie tokeny są testowe
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest register) throws AppException {
        if (accountService.register(register))
            return new RegisterResponse(true, null, "Utworzono konto");
        return new RegisterResponse(false, null, "Nieudana rejestracja");
    }

}
