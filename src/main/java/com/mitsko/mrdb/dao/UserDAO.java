package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Status;

import java.util.ArrayList;

public interface UserDAO{
    User takeUserByLoginAndPassword(String login, String password);

    int registration(User newUser);

    ArrayList<String> takeAllLogins();

    String takePassword(String login);

    void updateRating(int userID, int newRating);

    int takeRating(int userID);

    Status takeStatus(int userID);

    boolean findLogin(String login);

    ArrayList<Integer> takeAllUsersID();

    int takeID(String login);

    String takeLogin(int userID);
}
