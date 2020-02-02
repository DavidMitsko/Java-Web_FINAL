package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.DAOFactory;
import com.mitsko.mrdb.dao.RecountDAO;
import com.mitsko.mrdb.dao.UserDAO;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Status;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.UserService;
import com.mitsko.mrdb.service.util.Crypto;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private RecountDAO recountDAO;
    private Crypto crypto;

    public UserServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        userDAO = daoFactory.getSQLUserDAO();
        recountDAO = daoFactory.getSQLRecountDAO();

        crypto = new Crypto();
    }

    @Override
    public User signIn(String login, String password) throws ServiceException {
        if(login.equals("") || password.equals("")) {
            throw new ServiceException("Wrong parameter");
        }

        String passwordInDB = userDAO.takePassword(login);

        if(!crypto.checkPassword(password, passwordInDB)) {
            throw new ServiceException("Wrong login or password");
        }

        User user = userDAO.takeUserByLoginAndPassword(login, passwordInDB);
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

        if(userDAO.findLogin(login)) {
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

    @Override
    public ArrayList<String> takeAllLogins() throws ServiceException {

        return userDAO.takeAllLogins();
    }

    @Override
    public Status takeStatus(String login) throws ServiceException {
        if(login.equals("")) {
            throw new ServiceException("Wrong parameter");
        }
        int id = userDAO.takeID(login);
        return userDAO.takeStatus(id);
    }

    @Override
    public String takeLogin(int userID) throws ServiceException {
        return userDAO.takeLogin(userID);
    }

    @Override
    public int reestablishUsersRating(int userID, int movieID) {
        int usersRating = userDAO.takeRating(userID);
        int direct = recountDAO.takeDirect(userID, movieID);
        if (usersRating == 0 && direct == -1) {
            return usersRating;
        }
        if (usersRating == 10 && direct == 1) {
            return usersRating;
        }
        return usersRating + direct;
    }

    @Override
    public int takeAverageRating(String userLogin) {
        int userID = userDAO.takeID(userLogin);
        return userDAO.takeRating(userID);
    }
}
