package cinex.controller;

import cinex.BackApplication;
import cinex.GlobalTestValues;
import cinex.controller.api.responses.MovieRatingResponse;
import cinex.repository.MovieRepository;
import cinex.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Collections;

import static cinex.GlobalTestValues.getToken;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


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
    @Autowired
    private UserService userService;

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
    @Test
    public void testAddRatingWithoutLoggedUser(){
        var movie = GlobalTestValues.getMovie();
        movie = movieRepository.save(movie);
        String json = String.format(
                "{\"movieId\":\"%s\",\"rating\":3}",
                movie.getId().toString()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        var response = testRestTemplate.exchange(
                GlobalTestValues.url + port + "/movieRatings/addRating",
                HttpMethod.PUT,
                entity,
                MovieRatingResponse.class
        );

        movieRepository.delete(movie);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().success);
        assertEquals(response.getBody().message, "You are not logged in");

    }
    @Test
    public void testAddRatingWithoutCorrectMovie(){
        String json = "{\"movieId\":\"00000000-0000-0000-0000-000000000000\",\"rating\":3}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        var response = testRestTemplate.exchange(
                GlobalTestValues.url + port + "/movieRatings/addRating",
                HttpMethod.PUT,
                entity,
                MovieRatingResponse.class
        );

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().success);
        assertEquals(response.getBody().message, "There is no such film.");
    }
}