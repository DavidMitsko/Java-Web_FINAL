package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Status;

import java.util.ArrayList;

public interface UserDAO{
    User takeUserByLoginAndPassword(String login, String password) throws DAOException;

    int registration(User newUser) throws DAOException;

    ArrayList<String> takeAllLogins() throws DAOException;

    String takePassword(String login) throws DAOException;

    void updateRating(int userID, int newRating) throws DAOException;

    int takeRating(int userID) throws DAOException;

    Status takeStatus(int userID) throws DAOException;

    boolean findLogin(String login) throws DAOException;

    ArrayList<Integer> takeAllUsersID() throws DAOException;

    int takeID(String login) throws DAOException;

    String takeLogin(int userID) throws DAOException;
}
