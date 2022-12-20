package pis.skalowalnosc.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ExampleController {

    @GetMapping
    public String helloWorld() {
        return "Hello World from Spring Boot";
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        return "Goodbye";
    }
}
