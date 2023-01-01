package cinex.db;

import cinex.BackApplication;
import cinex.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = BackApplication.class)
class DBTests {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test
    void checkIfDatabaseNotEmpty(){
        String sql = "SELECT * FROM public.movie";

        List<Movie> listMovies = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Movie.class));

        assertTrue(listMovies.size() > 0);
    }
}
