package pis.skalowalnosc.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pis.skalowalnosc.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pis.skalowalnosc.GlobalTestValues.getUser;
import static pis.skalowalnosc.GlobalTestValues.username;

@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @AfterEach
    public void clean() {
        List<User> users;
        if (!(users = userRepository.findByUsername(username)).isEmpty())
            userRepository.deleteAll(users);
    }

    @Test
    public void testAdd() {
        var user = getUser();

        var DBUser = userRepository.save(user);

        assertEquals(DBUser.getUsername(), user.getUsername());
        assertTrue(DBUser.getRatings().isEmpty());
        assertEquals('1', DBUser.getRole());
        assertEquals(user.getHash(), DBUser.getHash());
        assertEquals(user.getSalt(), DBUser.getSalt());
    }
}
