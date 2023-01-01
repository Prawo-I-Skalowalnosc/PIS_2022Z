package cinex.service;

import cinex.controller.api.requests.LoginRequest;
import cinex.controller.api.requests.RegisterRequest;
import cinex.errors.AppException;
import cinex.model.User;

import java.util.Optional;

public interface UserService {
    User login(LoginRequest login) throws AppException;

    boolean register(RegisterRequest register) throws AppException;

    void validatePassword(String password) throws AppException;

    void validateMail(String mail) throws AppException;

    byte[] hashPassword(String password, byte[] salt) throws AppException;

    Optional<User> loadByUsername(String username);

    String generateToken(User user);
}
