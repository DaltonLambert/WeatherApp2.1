package com.dalton.WeatherApp.dao;

import com.dalton.WeatherApp.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    boolean createUser(String username, String passwordHash);
}
