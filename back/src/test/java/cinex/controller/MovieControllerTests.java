package cinex.controller;

import cinex.BackApplication;
import cinex.GlobalTestValues;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static cinex.GlobalTestValues.getToken;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackApplication.class)
public class MovieControllerTests {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private MovieController movieController;

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
    public void testGetFirst() throws JSONException {
        var result = testRestTemplate.getForEntity(GlobalTestValues.url + port + "/movies/first", String.class);
        assertTrue(result.hasBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        var body = new JSONObject(result.getBody());
        assertNotNull(body);
        assertNotNull(body.get("id"));
        assertNotNull(body.get("genre"));
        assertNotNull(body.get("language"));
        assertNotNull(body.get("releaseDate"));
        assertNotNull(body.get("title"));
        assertNotNull(body.get("rating"));
    }

    @Test
    public void testGetBest() throws JSONException {
        var result = testRestTemplate.getForEntity(GlobalTestValues.url + port + "/movies/best", String.class);
        assertTrue(result.hasBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        var body = new JSONArray(result.getBody());
        assertNotNull(body);
        assertTrue(body.length() > 1);
    }
}
