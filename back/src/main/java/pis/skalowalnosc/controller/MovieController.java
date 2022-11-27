package pis.skalowalnosc.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pis.skalowalnosc.service.MovieService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @RequestMapping("/first")
    public String firstTitle() {
        var title = movieService.findAll().get(0).getTitle();
        var json = new JSONObject();
        json.put("result", title);
        return json.toString();
    }
}
