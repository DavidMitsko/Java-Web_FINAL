package com.mitsko.mrdb.service;

import com.mitsko.mrdb.entity.Movie;

import java.util.ArrayList;

public interface MovieService {
    void updateRating(int movieID) throws ServiceException;

    float takeAverageRating(String movieName) throws ServiceException;

    ArrayList<Movie> takeAllMovies() throws ServiceException;

    void addMovie(String movieName, String imageName, String description) throws ServiceException;

    void removeMovie(String movieName) throws ServiceException;

    String takeDescription(String movieName) throws ServiceException;

    String takeNameByID(int movieID) throws ServiceException;
}
