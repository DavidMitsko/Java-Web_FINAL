package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Status;

import java.util.ArrayList;

public interface UserDAO {
    User signIn(String login, String password);

    int registration(User newUser);

    ArrayList takeAllLogins();

    String takePassword(String login);

    void updateRating(String login, int newRating);

    int takeRating(String login);

    Status takeStatus(String login);
}
