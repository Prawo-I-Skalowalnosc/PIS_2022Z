package cinex.controller;

import cinex.BackApplication;
import cinex.GlobalTestValues;
import cinex.model.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static cinex.GlobalTestValues.getUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static cinex.GlobalTestValues.getToken;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackApplication.class)
@AutoConfigureMockMvc
public class MovieRatingControllerTests {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private MovieRatingController movieRatingController;
    @Autowired
    private MockMvc mvc;
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
        assertThat(movieRatingController).isNotNull();
    }
    @Test
    public void testAddRating() throws Exception {
        String json = "{\"raterId\":\"a45da728-3a40-4c2c-8028-946ca33be960\",\"movieId\":\"c2d29867-3d0b-d497-9191-18a9d8ee7831\",\"rating\":3}";
//        SecurityContextHolder.setContext(
//                SecurityContextHolder.createEmptyContext()
//        );
//        User rater = getUser();
//        SecurityContextHolder.getContext().setAuthentication(
//                new UsernamePasswordAuthenticationToken(rater.getUsername(), rater.getHash())
//        );
//        mvc.perform(put("/movieRatings/addRating")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                        .andExpect(status().isOk());

    }
}

