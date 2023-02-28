package com.dalton.WeatherApp.dao;

import com.dalton.WeatherApp.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sqlGetAllUsers = "SELECT * FROM users";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllUsers);
        while(results.next()){
            User user = mapRowToUser(results);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public boolean createUser(String username, String password) {
        String sqlCreateUser = "INSERT INTO weather (username, password_hash) VALUES (?, ?) returning id";
        String passwordHashValue = new BCryptPasswordEncoder().encode(password);

        return jdbcTemplate.update(sqlCreateUser, username, passwordHashValue) == 1;
    }

    public User mapRowToUser(SqlRowSet results){
        User user = new User();
        user.setId(results.getInt("id"));
        user.setUsername(results.getString("username"));
        user.setPassword(results.getString("password_hash"));
        return user;
    }
}
