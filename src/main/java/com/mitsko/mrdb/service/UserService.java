package com.mitsko.mrdb.service;

import com.mitsko.mrdb.entity.User;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;

    User registration(String login, String password) throws ServiceException;
}
