package com.dalton.WeatherApp.controller;


import com.dalton.WeatherApp.dao.JdbcUserDao;
import com.dalton.WeatherApp.dao.UserDao;
import com.dalton.WeatherApp.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST, consumes = "application/json")
    public User createUser(@RequestBody User user) {
        return userDao.createUser(user);
    }



}
