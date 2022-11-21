package pis.skalowalnosc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pis.skalowalnosc.model.Movie;

import java.util.List;

@SpringBootApplication
public class DBTest implements CommandLineRunner {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DBTest.class, args);
    }

    public void testListAll() {
        String sql = "SELECT * FROM public.movie";

        List<Movie> listMovies = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Movie.class));
        for (Movie movie : listMovies) {
            System.out.println(movie.getTitle());
        }
    }

    @Override
    public void run(String... args) throws Exception {
        testListAll();
    }
}
