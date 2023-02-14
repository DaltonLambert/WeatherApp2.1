package com.dalton.WeatherApp.dao;

import com.dalton.WeatherApp.model.Weather;

import java.util.List;

public interface ForecastDao {
    List<Weather> findByLocation(String location);
}
