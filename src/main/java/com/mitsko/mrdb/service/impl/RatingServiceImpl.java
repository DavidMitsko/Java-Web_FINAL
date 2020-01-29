package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.DAOFactory;
import com.mitsko.mrdb.dao.MovieDAO;
import com.mitsko.mrdb.dao.RatingDAO;
import com.mitsko.mrdb.dao.UserDAO;
import com.mitsko.mrdb.entity.Rating;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.service.MovieService;
import com.mitsko.mrdb.service.RatingService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import java.util.ArrayList;

public class RatingServiceImpl implements RatingService {
    private RatingDAO ratingDAO;
    private UserDAO userDAO;
    private MovieDAO movieDAO;

    public RatingServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ratingDAO = daoFactory.getSQLRatingDAO();
        userDAO = daoFactory.getSQLUserDAO();
        movieDAO = daoFactory.getSQLMovieDAO();
    }

    @Override
    public void addNewRating(User user, String movieName, float rating) throws ServiceException {
        if (movieName.equals("") || rating < 0) {
            throw new ServiceException("Wrong parameter");
        }
        float oldRating = ratingDAO.takeUsersRatingOfMovie(user.getLogin(), movieName);
        if(oldRating != -1) {
            updateRating(user.getLogin(), movieName, rating);
            return;
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getMovieService();

        Rating newRating = new Rating(user.getLogin(), movieName, rating);
        ratingDAO.addNewRating(newRating);

        movieService.updateRating(movieName);

        ArrayList<String> usersLogin = userDAO.takeAllLogins();
        updateRating(movieName, usersLogin);
    }

    private boolean checkForUpdate(String movieName) {
        int countOfRating = movieDAO.takeCountOfRating(movieName);
        if (countOfRating >= 5) {
            countOfRating = 0;
            movieDAO.updateCountOfRating(countOfRating, movieName);
            return true;
        } else {
            countOfRating++;
            movieDAO.updateCountOfRating(countOfRating, movieName);
            return false;
        }
    }

    private void updateRating(String movieName, ArrayList<String> userLogin) {
        float movieRating = movieDAO.takeRatingOfMovie(movieName);

        if (checkForUpdate(movieName)) {
            for (String login : userLogin) {
                float usersRatingOfMovie = ratingDAO.takeUsersRatingOfMovie(login, movieName);
                if(usersRatingOfMovie == -1) {
                    continue;
                }
                int userRating = userDAO.takeRating(login);
                float difference = movieRating - usersRatingOfMovie;
                if (difference > 3 || difference < -3) {
                    if (userRating != 0) {
                        userDAO.updateRating(login, --userRating);
                    }
                }
                if (difference < 1 && difference > -1) {
                    if (userRating != 10) {
                        userDAO.updateRating(login, ++userRating);
                    }
                }
            }
        }
    }

    @Override
    public void updateRating(String userLogin, String movieName, float rating) throws ServiceException {
        if (userLogin.equals("") || movieName.equals("") || rating < 0) {
            throw new ServiceException("Wrong parameter");
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getMovieService();

        Rating newRating = new Rating(userLogin, movieName, rating);
        ratingDAO.updateRating(newRating);

        movieService.updateRating(movieName);
    }
}
