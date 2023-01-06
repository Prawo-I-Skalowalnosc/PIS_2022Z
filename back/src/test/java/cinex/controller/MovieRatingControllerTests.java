package cinex.controller;

import cinex.BackApplication;
import cinex.repository.MovieRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.util.Collections;

import static cinex.GlobalTestValues.getToken;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackApplication.class)
public class MovieRatingControllerTests {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private MovieController movieController;
    @Autowired
    private MovieRepository movieRepository;

    @Before
    public void setup() {
        testRestTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders()
                            .add("Authorization", getToken());
                    return execution.execute(request, body);
                }));
    }

    @Test
    public void testExists() {
        assertThat(movieController).isNotNull();
    }
}