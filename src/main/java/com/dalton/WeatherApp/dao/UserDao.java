package com.dalton.WeatherApp.dao;

import com.dalton.WeatherApp.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();



    User createUser(User user);
}
