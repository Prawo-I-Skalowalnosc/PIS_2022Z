package pis.skalowalnosc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pis.skalowalnosc.service.MovieService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/first")
    public String firstTitle() {
        return movieService.findAll().get(0).getTitle();
    }
}
