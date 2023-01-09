package cinex.repository;

import cinex.model.User;
import cinex.security.UserRoles;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static cinex.GlobalTestValues.getUser;
import static cinex.GlobalTestValues.username;

@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @AfterEach
    public void clean() {
        Optional<User> user;
        if ((user = userRepository.findByUsername(username)).isPresent())
            userRepository.delete(user.get());
    }
    @Test
    public void testAdd() {
        var user = getUser();

        var DBUser = userRepository.save(user);

        assertEquals(DBUser.getUsername(), user.getUsername());
        assertTrue(DBUser.getRatings().isEmpty());
        assertEquals(List.of(UserRoles.ADMIN, UserRoles.MODERATOR, UserRoles.USER), DBUser.getUserRoles());
        assertEquals(user.getHash(), DBUser.getHash());
        assertEquals(user.getSalt(), DBUser.getSalt());
    }
}
