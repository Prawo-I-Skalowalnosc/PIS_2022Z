package cinex.service;

import cinex.controller.api.requests.LoginRequest;
import cinex.controller.api.requests.RegisterRequest;
import cinex.errors.AppException;
import cinex.model.User;
import cinex.repository.UserRepository;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.Optional;

import static cinex.GlobalTestValues.getUser;
import static cinex.GlobalTestValues.username;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.fail;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @BeforeEach
    @AfterEach
    public void clean() {
        Optional<User> user;
        if ((user = userRepository.findByUsername(username)).isPresent())
            userRepository.delete(user.get());
    }

    @Test
    public void testValidation_Passed() {
        try {
            userService.validatePassword("PIS123_abc");
            userService.validateMail("test@gmail.com");
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testValidation_TooLong() {
        var longString = new StringBuilder();
        longString.append("123abc".repeat(1000));
        assertThrows(AppException.class, () -> userService.validatePassword(longString.toString()));
        assertThrows(AppException.class, () -> userService.validateMail(longString.toString()));
    }

    @Test
    public void testValidation_TooShort() {
        var string = "a";
        assertThrows(AppException.class, () -> userService.validatePassword(string));
        assertThrows(AppException.class, () -> userService.validateMail(string));
    }

    @Test
    public void testValidation_OnlyWhiteChars() {
        var string = "   ";
        assertThrows(AppException.class, () -> userService.validatePassword(string));
        assertThrows(AppException.class, () -> userService.validateMail(string));
    }

    @Test
    public void testLogin_Passed() throws AppException {
        var random = new SecureRandom();
        var salt = new byte[16];
        random.nextBytes(salt);

        var user = new User(getUser().getUsername(),
                userService.hashPassword(getUser().getHash(), salt), "mail123@pl.com", salt);

        userRepository.save(user);

        var request = new LoginRequest(getUser().getUsername(), getUser().getHash());

        var logged = userService.login(request);

        Assertions.assertNotNull(logged);
        assertEquals(logged.getUsername(), request.username);
        assertEquals(logged.getHash(), Hex.encodeHexString(userService.hashPassword(request.password, salt)));
    }

    @Test
    public void testLogin_NonExisting() {
        var request = new LoginRequest(getUser().getUsername(), getUser().getHash());
        assertThrows(AppException.class, () ->  userService.login(request));
    }

    @Test
    public void testLogin_NonWrongPassword() {
        userRepository.save(getUser());
        var request = new LoginRequest(getUser().getUsername(), "losowe hasło");
        assertThrows(AppException.class, () ->  userService.login(request));
    }

    @Test
    public void testRegister_NonMatchingPassword() {
        var request = new RegisterRequest(getUser().getUsername(),
                "losowe hasło", "2", "gmail@121424.com");
        assertThrows(AppException.class, () ->  userService.register(request));
    }

    @Test
    public void testRegister_NoPassword() {
        var request = new RegisterRequest(getUser().getUsername(),
                "", "", "gmail@121424.com");
        assertThrows(AppException.class, () ->  userService.register(request));
    }

    @Test
    public void testRegister_AlreadyExists() {
        userRepository.save(getUser());
        var request = new RegisterRequest(getUser().getUsername(),
                "hasełko", "hasełko", "gmail@121424.com");
        assertThrows(AppException.class, () ->  userService.register(request));
    }

    @Test
    public void testRegister_Correct() throws AppException {
        var request = new RegisterRequest(getUser().getUsername(),
                "hasełko", "hasełko", "gmail@121424.com");

        assertTrue(userService.register(request));

        var user = userRepository.findByUsername(getUser().getUsername());

        assertTrue(user.isPresent());
        assertEquals(user.get().getEmail(), request.email);
        assertEquals(user.get().getUsername(), request.username);
        assertFalse(user.get().isAdmin());
    }

    @Test
    public void testRegister_AndLogin() throws AppException {
        var request = new RegisterRequest(getUser().getUsername(),
                "hasełko", "hasełko", "gmail@121424.com");

        assertTrue(userService.register(request));

        var user = userRepository.findByUsername(getUser().getUsername());

        assertTrue(user.isPresent());

        var loginrequest = new LoginRequest(getUser().getUsername(), request.password);

        var logged = userService.login(loginrequest);

        Assertions.assertNotNull(logged);
        assertEquals(logged.getUsername(), loginrequest.username);
        assertEquals(logged.getId(), user.get().getId());
        assertEquals(logged.getRole(), user.get().getRole());
    }

    @Test
    public void testLoadByUsername() {
        var absent = userService.loadByUsername(getUser().getUsername());
        assertFalse(absent.isPresent());

        userRepository.save(getUser());

        var present = userService.loadByUsername(getUser().getUsername());
        assertTrue(present.isPresent());
        assertEquals(present.get().getHash().strip(), getUser().getHash().strip());
        assertEquals(present.get().getRatings().size(), 0);
        assertEquals(present.get().getRole(), getUser().getRole());
    }
}
