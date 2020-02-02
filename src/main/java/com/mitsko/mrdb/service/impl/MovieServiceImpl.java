package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.*;
import com.mitsko.mrdb.entity.Movie;
import com.mitsko.mrdb.service.*;

import java.util.ArrayList;

public class MovieServiceImpl implements MovieService {
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
        /*if(movieID.equals("")) {
            throw new ServiceException("Wrong parameter");
        }*/

        float newRating = ratingDAO.takeAverageRatingOfMovie(movieID);
        if(newRating == -1) {
            throw new ServiceException();
        }

        movieDAO.updateRating(newRating, movieID);
    }

    @Override
    public float takeAverageRating(String movieName) throws ServiceException {
        if(movieName.equals("")) {
            throw new ServiceException("Wrong parameter");
        }

        int id = movieDAO.takeID(movieName);
        float rating = movieDAO.takeRatingOfMovie(id);

        if(rating == -1) {
            throw new ServiceException();
        }
        return rating;
    }

    @Override
    public ArrayList<Movie> takeAllMovies() throws ServiceException {
        ArrayList<Movie> movieList = movieDAO.takeAllMovies();

        if(movieList.size() == 0) {
            throw new ServiceException();
        }
        for(Movie movie : movieList) {
            if(movie.getImageName() != null) {
                String imagePath = "../images/" + movie.getImageName();
                movie.setImageName(imagePath);
            }
        }

        return movieList;
    }

    @Override
    public void addMovie(String movieName, String imageName, String description) {
        Movie movie = new Movie(movieName, imageName, description);

        movieDAO.addMovie(movie);
    }

    @Override
    public void removeMovie(String movieName) {
        int movieID = movieDAO.takeID(movieName);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        ArrayList<Integer> usersID = userDAO.takeAllUsersID();
        for(int userID : usersID) {
            if(!recountDAO.find(userID, movieID)) {
                continue;
            }
            int userRating = userService.reestablishUsersRating(userID, movieID);
            userDAO.updateRating(userID, userRating);
        }

        try {
            RatingService ratingService = serviceFactory.getRatingService();
            ratingService.removeAllMoviesRatings(movieName);

            ReviewService reviewService = serviceFactory.getReviewService();
            reviewService.removeAllMoviesReview(movieName);

            movieDAO.removeMovie(movieName);
        } catch (ServiceException ex) {

        }
    }

    @Override
    public String takeDescription(String movieName) {
        int movieID = movieDAO.takeID(movieName);

        return movieDAO.takeDescription(movieID);
    }
}
