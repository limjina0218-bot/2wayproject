package com.example.twoway_movie.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class MovieController {

    @GetMapping("/")
    public String main() {
        return "main";
    }


}
