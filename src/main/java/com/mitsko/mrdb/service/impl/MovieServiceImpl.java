package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.DAOFactory;
import com.mitsko.mrdb.dao.MovieDAO;
import com.mitsko.mrdb.dao.RatingDAO;
import com.mitsko.mrdb.entity.Movie;
import com.mitsko.mrdb.service.MovieService;
import com.mitsko.mrdb.service.ServiceException;

import java.util.ArrayList;

public class MovieServiceImpl implements MovieService {
    private RatingDAO ratingDAO;
    private MovieDAO movieDAO;

    public MovieServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ratingDAO = daoFactory.getSQLRatingDAO();
        movieDAO = daoFactory.getSQLMovieDAO();
    }

    @Override
    public void updateRating(String movieName) throws ServiceException {
        if(movieName.equals("")) {
            throw new ServiceException("Wrong parameter");
        }

        float newRating = ratingDAO.takeAverageRatingOfMovie(movieName);
        if(newRating == -1) {
            throw new ServiceException();
        }

        movieDAO.updateRating(newRating, movieName);
    }

    @Override
    public float takeAverageRating(String movieName) throws ServiceException {
        if(movieName.equals("")) {
            throw new ServiceException("Wrong parameter");
        }

        float rating = movieDAO.takeRatingOfMovie(movieName);

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

        return movieList;
    }
}
