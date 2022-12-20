package pis.skalowalnosc.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pis.skalowalnosc.BackApplication;
import pis.skalowalnosc.model.Movie;

import java.util.List;

@SpringBootTest(classes = BackApplication.class)
class DBTests {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void checkIfDatabaseNotEmpty(){
        String sql = "SELECT * FROM public.movie";

        List<Movie> listMovies = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Movie.class));

        assert listMovies.size() > 0;
    }

}
