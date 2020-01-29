package com.mitsko.mrdb.service;

import com.mitsko.mrdb.entity.Movie;

import java.util.ArrayList;

public interface MovieService {
    void updateRating(String movieName) throws ServiceException;

    float takeAverageRating(String movieName) throws ServiceException;

    ArrayList<Movie> takeAllMovies() throws ServiceException;

    void addMovie(String movieName);
}
