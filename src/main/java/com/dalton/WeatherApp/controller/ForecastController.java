package com.dalton.WeatherApp.controller;

import com.dalton.WeatherApp.dao.JdbcWeatherDao;
import com.dalton.WeatherApp.model.Weather;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/forecast")
public class ForecastController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/location/{location}", method = RequestMethod.GET)
    public Weather findByLocation(@PathVariable String location) {
        // Define the API endpoint URL and your API key
        String apiUrl = "http://api.openweathermap.org/data/2.5/forecast?q={city}&appid={apiKey}&units=metric&cnt=5";
        String apiKey = System.getenv("API_KEY");


    }

}