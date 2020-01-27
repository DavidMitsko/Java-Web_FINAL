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
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getMovieService();

        Rating newRating = new Rating(user.getLogin(), movieName, rating);
        ratingDAO.addNewRating(newRating);

        movieService.updateRating(movieName);

        if (movieDAO.takeCountOfRating(movieName) == 5) {
            int userRating = user.getAverageRating();
            float movieRating = movieDAO.takeRatingOfMovie(movieName);
            float difference = movieRating - userRating;
            if (difference > 2 || difference < -2) {
                if (userRating != 0) {
                    user.setAverageRating(userRating - 1);
                    userDAO.updateRating(user.getLogin(), user.getAverageRating());
                }
            }
            if (difference < 1 || difference > -1) {
                if (userRating != 10) {
                    user.setAverageRating(userRating + 1);
                    userDAO.updateRating(user.getLogin(), user.getAverageRating());
                }
            }
        }
    }

    @Override
    public void updateRating(String userLogin, String movieName, float rating) throws ServiceException {
        if (userLogin.equals("") || movieName.equals("") || rating < 0) {
            throw new ServiceException("Wrong parameter");
        }

        Rating newRating = new Rating(userLogin, movieName, rating);
        ratingDAO.updateRating(newRating);
    }
}
