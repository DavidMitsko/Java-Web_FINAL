package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.DAOFactory;
import com.mitsko.mrdb.dao.UserDAO;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.UserService;
import com.mitsko.mrdb.service.util.Crypto;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private Crypto crypto;

    public UserServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        userDAO = daoFactory.getSQLUserDAO();

        crypto = new Crypto();
    }

    @Override
    public User signIn(String login, String password) throws ServiceException {
        if(login.equals("") || password.equals("")) {
            throw new ServiceException("Wrong parameter");
        }

        String passwordInDB = userDAO.takePassword(login);

        String hashPassword = crypto.encode(password);

        if(!passwordInDB.equals(hashPassword) || passwordInDB == null) {
            throw new ServiceException("Wrong login or password");
        }

        User user = userDAO.signIn(login, hashPassword);
        if(user == null) {
            throw new ServiceException();
        }
        return user;
    }

    @Override
    public User registration(String login, String password) throws ServiceException{
        if(login.equals("") || password.equals("")) {
            throw new ServiceException("Wrong parameter");
        }

        ArrayList<String> logins = userDAO.takeAllLogins();
        if(logins.contains(login)) {
            throw new ServiceException("Already exist a user with this name");
        }

        String hashPassword = crypto.encode(password);

        User user = new User(login, hashPassword);
        int id = userDAO.registration(user);
        if(id == -1) {
            throw new ServiceException();
        }
        user.setID(id);

        return user;
    }
}
