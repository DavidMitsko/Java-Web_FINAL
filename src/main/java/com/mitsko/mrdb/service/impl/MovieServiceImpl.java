package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.*;
import com.mitsko.mrdb.entity.Movie;
import com.mitsko.mrdb.service.*;

import java.util.ArrayList;

public class MovieServiceImpl implements MovieService {
    private final static String imgaePath = "../images/";

    private RatingDAO ratingDAO;
    private MovieDAO movieDAO;
    private RecountDAO recountDAO;
    private UserDAO userDAO;

    public MovieServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ratingDAO = daoFactory.getSQLRatingDAO();
        movieDAO = daoFactory.getSQLMovieDAO();
        userDAO = daoFactory.getSQLUserDAO();
        recountDAO = daoFactory.getSQLRecountDAO();
    }

    @Override
    public void updateRating(int movieID) throws ServiceException {
        try {
            float newRating = ratingDAO.takeAverageRatingOfMovie(movieID);
            if (newRating == -1) {
                throw new ServiceException();
            }

            movieDAO.updateRating(newRating, movieID);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public float takeAverageRating(String movieName) throws ServiceException {
        float rating;
        try {
            int id = movieDAO.takeID(movieName);
            rating = movieDAO.takeRatingOfMovie(id);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }

        if (rating == -1) {
            throw new ServiceException();
        }
        return rating;
    }

    @Override
    public ArrayList<Movie> takeAllMovies() throws ServiceException {
        ArrayList<Movie> movieList;
        try {
            movieList = movieDAO.takeAllMovies();
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }

        if (movieList.size() == 0) {
            throw new ServiceException();
        }
        for (Movie movie : movieList) {
            if (movie.getImageName() != null) {
                String imagePath = imgaePath + movie.getImageName();
                movie.setImageName(imagePath);
            }
        }

        return movieList;
    }

    @Override
    public void addMovie(String movieName, String imageName, String description) throws ServiceException {
        Movie movie = new Movie(movieName, imageName, description);

        try {
            movieDAO.addMovie(movie);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void removeMovie(String movieName) throws ServiceException {
        try {
            int movieID = movieDAO.takeID(movieName);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService userService = serviceFactory.getUserService();

            ArrayList<Integer> usersID = userDAO.takeAllUsersID();
            for (int userID : usersID) {
                if (!recountDAO.find(userID, movieID)) {
                    continue;
                }
                int userRating = userService.reestablishUsersRating(userID, movieID);
                userDAO.updateRating(userID, userRating);
            }

            RatingService ratingService = serviceFactory.getRatingService();
            ratingService.removeAllMoviesRatings(movieName);

            ReviewService reviewService = serviceFactory.getReviewService();
            reviewService.removeAllMoviesReview(movieName);

            movieDAO.removeMovie(movieName);
        } catch (ServiceException ex) {

        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public String takeDescription(String movieName) throws ServiceException {
        String description = null;
        try {
            int movieID = movieDAO.takeID(movieName);

            description = movieDAO.takeDescription(movieID);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return description;
    }

    @Override
    public String takeNameByID(int movieID) throws ServiceException {
        try {
            return movieDAO.takeName(movieID);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
