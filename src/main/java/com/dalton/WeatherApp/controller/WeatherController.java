package com.dalton.WeatherApp.controller;

import com.dalton.WeatherApp.dao.JdbcWeatherDao;
import com.dalton.WeatherApp.model.Weather;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/weather")
public class WeatherController {

    private final JdbcWeatherDao weatherDao;

    public WeatherController(JdbcWeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Weather createWeather(@RequestBody Weather weather) {
        return weatherDao.createWeather(weather);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Weather> findAll() {
        // Initialize the list that will store the weather data
        List<Weather> weatherList = new ArrayList<>();

        // Define the API endpoint URL and your API key
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";
        String apiKey = System.getenv("API_KEY");

        // Define the cities that you want to retrieve weather information for
        String[] cities = {"London", "Paris", "Florida", "Tokyo", "Tampa", "New York", "Miami"};

        // Use the RestTemplate to make a request to the OpenWeatherMap API for each city
        RestTemplate restTemplate = new RestTemplate();
        for (String city : cities) {
            Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class, city, apiKey);


            // Extract the location name and temperature from the response
            String locationName = (String) response.get("name");
            Integer humidity = (Integer) ((Map<String, Object>) response.get("main")).get("humidity");
            String description = (String) response.get("description");
            List<Map<String, Object>> weather = (List<Map<String, Object>>) response.get("weather");
            String icon = (String) weather.get(0).get("icon");
            // Check if the humidity value is not null
            if(humidity == null){
                // Throw an exception if the humidity value is null
                throw new NullPointerException("Humidity value is null");
            }

            // Convert the humidity value to int
            int humidityValue = humidity.intValue();
            double temperature = (double) ((Map<String, Object>) response.get("main")).get("temp");

            // Convert the temperature from Kelvin to Fahrenheit
            double temperatureFahrenheit = (temperature - 273.15) * 9 / 5 + 32;

            // Create a new Weather object and add it to the weatherList
            weatherList.add(new Weather(locationName, temperatureFahrenheit, humidityValue, description, icon));
        }

        return weatherList;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Weather findById(@PathVariable int id) {
        return weatherDao.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/location/{location}", method = RequestMethod.GET)
    public Weather findByLocation(@PathVariable String location){
        // Define the API endpoint URL and your API key
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";
        String apiKey = System.getenv("API_KEY");

        // Use the RestTemplate to make a request to the OpenWeatherMap API for the specified location
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class, location, apiKey);

        // Extract the location name and temperature from the response
        String locationName = (String) response.get("name");
        Integer humidity = (Integer) ((Map<String, Object>) response.get("main")).get("humidity");
        List<Map<String, Object>> weather = (List<Map<String, Object>>) response.get("weather");
        String description = (String) weather.get(0).get("main");
        String icon = (String) weather.get(0).get("icon");

        // Check if the humidity value is not null
        if(humidity == null){
            // Throw an exception if the humidity value is null
            throw new NullPointerException("Humidity value is null");
        }

        // Convert the humidity value to int
        int humidityValue = humidity.intValue();

        double temperature = (double) ((Map<String, Object>) response.get("main")).get("temp");

        // Convert the temperature from Kelvin to Fahrenheit
        double temperatureFahrenheit = (temperature - 273.15) * 9 / 5 + 32;

        temperatureFahrenheit = Math.round(temperatureFahrenheit);

        // Create a new Weather object and return it
        return new Weather(locationName, temperatureFahrenheit, humidityValue, description, icon);
    }


}
