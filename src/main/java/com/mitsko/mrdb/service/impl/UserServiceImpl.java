package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.DAOException;
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

        User user = null;
        try {
            String passwordInDB = userDAO.takePassword(login);

            if (!crypto.checkPassword(password, passwordInDB)) {
                throw new ServiceException("Wrong login or password");
            }

            user = userDAO.takeUserByLoginAndPassword(login, passwordInDB);
            if (user == null) {
                throw new ServiceException();
            }
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return user;
    }

    @Override
    public User registration(String login, String password) throws ServiceException{
        if(login.equals("") || password.equals("")) {
            throw new ServiceException("Wrong parameter");
        }

        User user = null;
        try {
            if (userDAO.findLogin(login)) {
                throw new ServiceException("Already exist a user with this name");
            }

            String hashPassword = crypto.encode(password);

            user = new User(login, hashPassword);
            int id = userDAO.registration(user);
            if (id == -1) {
                throw new ServiceException();
            }
            user.setID(id);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return user;
    }

    @Override
    public ArrayList<String> takeAllLogins() throws ServiceException {
        try {
            return userDAO.takeAllLogins();
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Status takeStatus(String login) throws ServiceException {
        if(login.equals("")) {
            throw new ServiceException("Wrong parameter");
        }
        try {
            int id = userDAO.takeID(login);
            return userDAO.takeStatus(id);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public String takeLogin(int userID) throws ServiceException {
        try {
            return userDAO.takeLogin(userID);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public int reestablishUsersRating(int userID, int movieID) throws ServiceException {
        try {
            int usersRating = userDAO.takeRating(userID);
            int direct = recountDAO.takeDirect(userID, movieID);
            if (usersRating == 0 && direct == -1) {
                return usersRating;
            }
            if (usersRating == 10 && direct == 1) {
                return usersRating;
            }
            return usersRating + direct;
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public int takeAverageRating(String userLogin) throws ServiceException {
        try {
            int userID = userDAO.takeID(userLogin);
            return userDAO.takeRating(userID);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
