package com.dalton.WeatherApp.controller;

import com.dalton.WeatherApp.dao.JdbcWeatherDao;
import com.dalton.WeatherApp.model.Forecast;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/forecast")
public class ForecastController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/location/{location}", method = RequestMethod.GET)
    public List<Forecast> getForecast(@PathVariable String location) {
        // Define the API endpoint URL and your API key
        String apiUrl = "http://api.openweathermap.org/data/2.5/forecast?q={city}&appid={apiKey}&units=metric&cnt=5";
        String apiKey = System.getenv("API_KEY");

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response;
        try {
            response = restTemplate.getForObject(apiUrl, Map.class, location, apiKey);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found", ex);
        }

        // check the response status code
        String status = (String) response.get("cod");
        if (!status.equals("200")) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve weather data");
        }

        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("list");
        List<Forecast> forecastList = new ArrayList<>();
        double high = Integer.MIN_VALUE;
        double low = Integer.MAX_VALUE;

        for (Map<String, Object> forecastData : weatherList) {
            Map<String, Object> main = (Map<String, Object>) forecastData.get("main");
            double tempMaxC = (double) main.get("temp_max");
            double tempMinC = (double) main.get("temp_min");
            double tempMaxF = (tempMaxC * 9 / 5) + 32;
            double tempMinF = (tempMinC * 9 / 5) + 32;

            if (tempMaxF > high) {
                high = tempMaxF;
            }
            if (tempMinF < low) {
                low = tempMinF;
            }
            high = Math.round(high);
            low = Math.round(low);

            // Retrieve the weather conditions for each day of the forecast
            List<Map<String, Object>> weatherConditions = (List<Map<String, Object>>) forecastData.get("weather");
            String icon = (String) weatherConditions.get(0).get("icon");

            forecastList.add(new Forecast(high, low, icon));
        }


        return forecastList;
    }

}
