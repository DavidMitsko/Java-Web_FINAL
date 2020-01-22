package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.User;

import java.sql.ResultSet;

public interface UserDAO {
    User signIn(String login, String password);
    void registration(User newUser);
    ResultSet takeAllLogins();
    String takeLogin(String login);
    void updateRating(String login, int newRating);
}
