package com.mitsko.mrdb.service;

import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Status;

import java.util.ArrayList;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;

    User registration(String login, String password) throws ServiceException;

    ArrayList<String> takeAllLogins() throws ServiceException;

    Status takeStatus(String login) throws ServiceException;

    String takeLogin(int UserID) throws ServiceException;

    int reestablishUsersRating(int userID, int movieID) throws ServiceException;

    int takeAverageRating(String userLogin) throws ServiceException;
}
