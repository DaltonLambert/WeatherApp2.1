package com.dalton.WeatherApp.dao;

import com.dalton.WeatherApp.model.Weather;

import java.util.List;

public interface WeatherDao {
    List<Weather> findAll();
    Weather findById(int id);

    List<Weather> findByLocation(String location);

    Weather createWeather(Weather weather);
}

