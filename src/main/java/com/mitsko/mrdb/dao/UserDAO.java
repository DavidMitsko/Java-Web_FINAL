package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.User;

import java.util.ArrayList;

public interface UserDAO {
    User signIn(String login, String password);

    int registration(User newUser);

    ArrayList takeAllLogins();

    String takeLogin(String login);

    void updateRating(String login, int newRating);
}
