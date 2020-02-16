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
import com.mitsko.mrdb.service.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {
    private final static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private UserDAO userDAO;
    private RecountDAO recountDAO;
    private Crypto crypto;
    private Validator validator;

    public UserServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        userDAO = daoFactory.getSQLUserDAO();
        recountDAO = daoFactory.getSQLRecountDAO();

        crypto = new Crypto();
        validator = new Validator();
    }

    @Override
    public User signIn(String login, String password) throws ServiceException {
        if(!validator.checkLogin(login) || !validator.checkPassword(password)) {
            logger.error("Wrong parameter");
            throw new ServiceException("Wrong parameter");
        }

        try {
            String passwordInDB = userDAO.takePassword(login);

            if (!crypto.checkPassword(password, passwordInDB)) {
                logger.error("Wrong password");
                throw new ServiceException("Wrong password");
            }

            User user = userDAO.takeUserByLoginAndPassword(login, passwordInDB);
            if (user == null) {
                logger.error("No user with this login and password");
                throw new ServiceException("No user with this login and password");
            }

            logger.debug("Sign in");
            return user;
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public User registration(String login, String password) throws ServiceException{
        if(!validator.checkLogin(login) || !validator.checkPassword(password)) {
            logger.error("Wrong parameter");
            throw new ServiceException("Wrong parameter");
        }

        try {
            if (userDAO.findLogin(login)) {
                logger.error("Already exist a user with this name");
                throw new ServiceException("Already exist a user with this name");
            }

            String hashPassword = crypto.encode(password);

            User user = new User(login, hashPassword);
            int id = userDAO.registration(user);
            if (id == -1) {
                logger.error("We have problem with db");
                throw new ServiceException("We have problem with db");
            }
            user.setID(id);

            logger.debug("Added new user");
            return user;
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }

    }

    @Override
    public ArrayList<String> takeAllLogins() throws ServiceException {
        try {
            ArrayList<String> usersLogin = userDAO.takeAllLogins();

            logger.debug("Received all users logins");
            return usersLogin;
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public Status takeStatus(String login) throws ServiceException {
        if(!validator.checkLogin(login)) {
            logger.error("Wrong parameter");
            throw new ServiceException("Wrong parameter");
        }
        try {
            int id = userDAO.takeID(login);
            Status status = userDAO.takeStatus(id);

            logger.debug("Received users status");
            return status;
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public String takeLogin(int userID) throws ServiceException {
        try {
            String login = userDAO.takeLogin(userID);
            logger.debug("Received users login");
            return login;
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public int reestablishUsersRating(int userID, int movieID) throws ServiceException {
        try {
            int usersRating = userDAO.takeRating(userID);
            int direct = recountDAO.takeDirect(userID, movieID);
            recountDAO.removeRecount(userID, movieID);
            logger.debug("Removed recount note");

            logger.debug("Reestablish users rating");
            if (usersRating == 0 && direct == -1) {
                return usersRating;
            }
            if (usersRating == 10 && direct == 1) {
                return usersRating;
            }
            return usersRating - direct;
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public int takeAverageRating(String userLogin) throws ServiceException {
        try {
            int userID = userDAO.takeID(userLogin);
            int rating = userDAO.takeRating(userID);

            logger.debug("Received users rating");
            return rating;
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }
}
