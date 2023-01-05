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
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest login) throws AppException {
        var user = userService.login(login);
        return new LoginResponse(true, UserService.generateToken(user), "Zalogowano");
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest register) throws AppException {
        if (userService.register(register))
            return new RegisterResponse(true, null, "Utworzono konto");
        return new RegisterResponse(false, null, "Nieudana rejestracja");
    }

}
