package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.DAOFactory;
import com.mitsko.mrdb.dao.UserDAO;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.UserService;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {
    private DAOFactory daoFactory;
    private UserDAO userDAO;

    public UserServiceImpl() {
        daoFactory = DAOFactory.getInstance();
        userDAO = daoFactory.getSQLUserDAO();
    }

    @Override
    public User signIn(String login, String password) throws ServiceException {
        if(login.equals("") || password.equals("")) {
            throw new ServiceException("Wrong argument");
        }

        String passwordInDB = userDAO.takeLogin(login);
        if(!passwordInDB.equals(password) || passwordInDB == null) {
            throw new ServiceException("Wrong login or password");
        }

        User user = userDAO.signIn(login, password);
        if(user == null) {
            throw new ServiceException();
        }
        return user;
    }

    @Override
    public User registration(String login, String password) throws ServiceException{
        if(login.equals("") || password.equals("")) {
            throw new ServiceException("Wrong argument");
        }

        ArrayList<String> logins = userDAO.takeAllLogins();
        if(logins.contains(login)) {
            throw new ServiceException("Already exist a user with this name");
        }

        User user = new User(login, password);
        int id = userDAO.registration(user);
        if(id == -1) {
            throw new ServiceException();
        }
        user.setID(id);

        return user;
    }
}
