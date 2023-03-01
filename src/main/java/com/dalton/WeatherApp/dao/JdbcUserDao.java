package com.dalton.WeatherApp.dao;

import com.dalton.WeatherApp.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
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
    public User createUser(User user) {
        String sqlCreateUser = "INSERT INTO weather (username, password_hash) VALUES (?, ?) returning id";
        String passwordHashValue = new BCryptPasswordEncoder().encode(user.getPasswordHash());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlCreateUser, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, passwordHashValue);
            return ps;
        }, keyHolder);

        if (rowsAffected == 1) {
            user.setId(keyHolder.getKey().intValue());
            return user;
        } else {
            return null;
        }
    }



    public User mapRowToUser(SqlRowSet results){
        User user = new User();
        user.setId(results.getInt("id"));
        user.setUsername(results.getString("username"));
        user.setPassword(results.getString("password_hash"));
        return user;
    }
}
