package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.*;
import com.mitsko.mrdb.entity.Rating;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.service.MovieService;
import com.mitsko.mrdb.service.RatingService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import java.util.ArrayList;

import static java.lang.Float.NaN;

public class RatingServiceImpl implements RatingService {
    private RatingDAO ratingDAO;
    private UserDAO userDAO;
    private MovieDAO movieDAO;
    private RecountDAO recountDAO;

    public RatingServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ratingDAO = daoFactory.getSQLRatingDAO();
        userDAO = daoFactory.getSQLUserDAO();
        movieDAO = daoFactory.getSQLMovieDAO();
        recountDAO = daoFactory.getSQLRecountDAO();
    }

    @Override
    public void addNewRating(int userID, String movieName, float rating) throws ServiceException {
        if (movieName.equals("") || rating < 0) {
            throw new ServiceException("Wrong parameter");
        }
        int movieID = movieDAO.takeID(movieName);

        float oldRating = ratingDAO.takeUsersRatingOfMovie(userID, movieID);
        if(oldRating != -1) {
            updateRating(userID, movieID, rating);
            return;
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getMovieService();

        Rating newRating = new Rating(userID, movieID, rating);
        ratingDAO.addNewRating(newRating);

        movieService.updateRating(movieID);

        ArrayList<Integer> usersID = userDAO.takeAllUsersID();
        updateRating(movieID, usersID);
    }

    private boolean checkForUpdate(int movieID) {
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
    }

    private float calculateDifferenceInRatings(int userID, int movieID) {
        float movieRating = movieDAO.takeRatingOfMovie(movieID);
        float usersRatingOfMovie = ratingDAO.takeUsersRatingOfMovie(userID, movieID);
        if(usersRatingOfMovie == -1) {
            return NaN;
        }
        return movieRating - usersRatingOfMovie;
    }

    private void updateRating(int movieID, ArrayList<Integer> usersID) {
        if (checkForUpdate(movieID)) {
            for (int userID : usersID) {
                int userRating = userDAO.takeRating(userID);
                float difference = calculateDifferenceInRatings(userID, movieID);

                if(recountDAO.find(userID, movieID) || Float.isNaN(difference)) {
                    continue;
                }

                if (difference > 3 || difference < -3) {
                    if (userRating != 0) {
                        userDAO.updateRating(userID, --userRating);
                    }
                }
                if (difference < 1 && difference > -1) {
                    if (userRating != 10) {
                        userDAO.updateRating(userID, ++userRating);
                    }
                }
                recountDAO.add(userID, movieID);
            }
        }
    }

    private void updateRating(int userID, int movieID, float rating) throws ServiceException {
        /*if (userLogin.equals("") || movieID.equals("") || rating < 0) {
            throw new ServiceException("Wrong parameter");
        }*/

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getMovieService();

        Rating newRating = new Rating(userID, movieID, rating);
        ratingDAO.updateRating(newRating);

        movieService.updateRating(movieID);
    }
}
