package cinex.security;

import cinex.model.User;
import cinex.repository.UserRepository;
import cinex.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static cinex.GlobalTestValues.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AppAuthProviderTests {
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
    public void testAuthenticate_Ok() {
        userRepository.save(getUser());
        var auth = getAuth();

        var provider = new AppAuthenticationProvider(userService);
        var authResult = provider.authenticate(auth);

        assertNotNull(authResult);
        assertEquals(authResult.getPrincipal().getClass(), getUser().getClass());
        assertEquals(((User)authResult.getPrincipal()).getUsername(), getUser().getUsername());
        assertEquals(authResult.getCredentials(), getUser().getHash());
    }

    @Test
    public void testAuthenticate_Null() {
        var auth = getAuth();

        var provider = new AppAuthenticationProvider(userService);
        var authResult = provider.authenticate(auth);

        assertNull(authResult);
    }

    @Test
    public void testAuthenticate_NoPassword() {
        var auth = getAuth();

        var user = getUser();
        user.setHash("inne hasełko");
        userRepository.save(user);

        var provider = new AppAuthenticationProvider(userService);
        var authResult = provider.authenticate(auth);

        assertNull(authResult);
    }
}
