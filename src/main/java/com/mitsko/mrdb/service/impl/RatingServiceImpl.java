package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.*;
import com.mitsko.mrdb.entity.Rating;
import com.mitsko.mrdb.service.*;
import com.mitsko.mrdb.service.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class RatingServiceImpl implements RatingService {
    private final static Logger logger = LogManager.getLogger(RatingServiceImpl.class);

    private RatingDAO ratingDAO;
    private UserDAO userDAO;
    private MovieDAO movieDAO;
    private RecountDAO recountDAO;

    private Validator validator;

    public RatingServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ratingDAO = daoFactory.getSQLRatingDAO();
        userDAO = daoFactory.getSQLUserDAO();
        movieDAO = daoFactory.getSQLMovieDAO();
        recountDAO = daoFactory.getSQLRecountDAO();

        validator = new Validator();
    }

    @Override
    public boolean addNewRating(int userID, String movieName, float rating) throws ServiceException {
        if (validator.checkRating(rating)) {
            logger.error("Wrong parameter");
            throw new ServiceException("Wrong parameter");
        }
        try {
            int movieID = movieDAO.takeID(movieName);

            float oldRating = ratingDAO.takeUsersRatingOfMovie(userID, movieID);
            if (oldRating != -1) {
                logger.error("This user has rated " + movieName);
                return false;
            }

            Rating newRating = new Rating(userID, movieID, rating);
            ratingDAO.addNewRating(newRating);
            logger.debug("Added new rating");

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            MovieService movieService = serviceFactory.getMovieService();
            movieService.updateRating(movieName);
            logger.debug("Updated movies average rating");

            ArrayList<Integer> usersID = userDAO.takeAllUsersID();
            updateRating(movieID, usersID);
            logger.debug("Updated users ratings");
            return true;
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    private boolean checkForUpdate(int movieID) throws ServiceException {
        try {
            int countOfRating = movieDAO.takeCountOfRating(movieID);
            if (countOfRating >= 5) {
                countOfRating = 0;
                movieDAO.updateCountOfRating(countOfRating, movieID);
                return true;
            } else {
                countOfRating++;
                movieDAO.updateCountOfRating(countOfRating, movieID);
                return false;
            }
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    private void updateRating(int movieID, ArrayList<Integer> usersID) throws ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            if (checkForUpdate(movieID)) {
                for (int userID : usersID) {
                    int direct = 0;
                    int userRating = userDAO.takeRating(userID);

                    float movieRating = movieDAO.takeRatingOfMovie(movieID);
                    float usersRatingOfMovie = ratingDAO.takeUsersRatingOfMovie(userID, movieID);
                    if (usersRatingOfMovie == -1) {
                        continue;
                    }
                    float difference = movieRating - usersRatingOfMovie;

                    if (recountDAO.find(userID, movieID)) {
                        userRating = userService.reestablishUsersRating(userID, movieID);
                    }

                    if (difference > 3 || difference < -3) {
                        direct = -1;
                        if (userRating != 0) {
                            userDAO.updateRating(userID, --userRating);
                        }
                    }
                    if (difference < 1 && difference > -1) {
                        direct = 1;
                        if (userRating != 10) {
                            userDAO.updateRating(userID, ++userRating);
                        }
                    }
                    recountDAO.add(userID, movieID, direct);
                }
            }
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public void removeAllMoviesRatings(String movieName) throws ServiceException {
        try {
            int movieID = movieDAO.takeID(movieName);
            ratingDAO.removeAllRatings(movieID);

            logger.debug("Removed all ratings");
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }
}
