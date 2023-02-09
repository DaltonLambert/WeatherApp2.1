package com.dalton.WeatherApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/welcome")
    public String welcome(){
        return "Hello, Welcome to Spring Boot!";
    }
}
