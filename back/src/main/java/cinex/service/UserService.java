package cinex.service;

import cinex.controller.api.requests.LoginRequest;
import cinex.controller.api.requests.RegisterRequest;
import cinex.errors.AppException;
import cinex.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Optional;

import static cinex.service.UserServiceImpl.tokenSecret;

public interface UserService {
    User login(LoginRequest login) throws AppException;

    boolean register(RegisterRequest register) throws AppException;

    void validatePassword(String password) throws AppException;

    void validateMail(String mail) throws AppException;

    byte[] hashPassword(String password, byte[] salt) throws AppException;

    Optional<User> loadByUsername(String username);

    static String generateToken(User user) {
        return Jwts.builder()
            .setIssuer("CINEX")
            .setSubject(user.getUsername())
            .claim("admin", user.isAdmin())
            .claim("name", user.getUsername())
            .claim("password", user.getHash())
            .setIssuedAt(new Date())
            .signWith(
                    SignatureAlgorithm.HS256,
                    tokenSecret.getBytes()
            )
            .compact();
    }
}
