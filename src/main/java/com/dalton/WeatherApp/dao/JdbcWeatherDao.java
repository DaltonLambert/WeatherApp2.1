
package com.dalton.WeatherApp.dao;

import com.dalton.WeatherApp.model.Weather;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcWeatherDao implements WeatherDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcWeatherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Weather> findAll() {
        List<Weather> weathers = new ArrayList<>();
        String sql = "SELECT * FROM weather";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Weather weather = mapRowToWeather(results);
            weathers.add(weather);
        }
        return weathers;
    }

    @Override
    public Weather findById(int id) {
        String sql = "SELECT * FROM weather WHERE id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            return mapRowToWeather(results);
        } else {
            return null;
        }
    }
    @Override
    public List<Weather> findByLocation(String location){
        String sql = "SELECT * FROM weather WHERE location_name = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, location);
        List<Weather> weathers = new ArrayList<>();
        while(results.next()){
            Weather weather = mapRowToWeather(results);
            weathers.add(weather);
        }
        return weathers;
    }

    @Override
    public Weather createWeather(Weather weather){
        String sql = "INSERT INTO weather (location_name, temperature) VALUES (?, ?) returning id";
        int newId = jdbcTemplate.queryForObject(sql, int.class, weather.getLocation(), weather.getTemperature());
        return findById(newId);
    }

    public Weather mapRowToWeather(SqlRowSet results){
        Weather weather = new Weather();
        weather.setId(results.getInt("id"));
        weather.setLocation(results.getString("location_name"));
        weather.setTemperature(results.getDouble("temperature"));
        weather.setHumidity(results.getInt("humidity"));
        weather.setDescription(results.getString("description"));
        weather.setIcon(results.getString("icon"));
        return weather;
    }
}