package com.dalton.WeatherApp.controller;


import com.dalton.WeatherApp.dao.JdbcUserDao;
import com.dalton.WeatherApp.dao.JdbcWeatherDao;
import com.dalton.WeatherApp.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final JdbcUserDao userDao;

    public UserController(JdbcUserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
}
