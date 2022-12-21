package pis.skalowalnosc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pis.skalowalnosc.model.Movie;
import pis.skalowalnosc.service.MovieService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/first")
    public String first() {
        if (movieService.findAll().size() > 0)
            return movieService.findAll().get(0).getTitle();
        return "";
    }

    @GetMapping("/all")
    public List<Movie> all() {
        return movieService.findAll();
    }
}
