package cinex.controller;

import cinex.BackApplication;
import cinex.GlobalTestValues;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackApplication.class)
public class MovieControllerTests {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private MovieController movieController;
    @Test
    public void testExists() {
        assertThat(movieController).isNotNull();
    }
    @Test
    public void testGetFirst() {
        var result = testRestTemplate.getForEntity(GlobalTestValues.url + port + "/movies/first", String.class);
        assertTrue(result.hasBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
