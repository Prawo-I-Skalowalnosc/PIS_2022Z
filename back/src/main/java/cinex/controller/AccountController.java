package cinex.controller;

import cinex.controller.api.requests.LoginRequest;
import cinex.controller.api.requests.RegisterRequest;
import cinex.controller.api.responses.LoginResponse;
import cinex.controller.api.responses.RegisterResponse;
import cinex.errors.AppException;
import cinex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
