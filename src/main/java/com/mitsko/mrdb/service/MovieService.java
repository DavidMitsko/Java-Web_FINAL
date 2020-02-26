package com.mitsko.mrdb.service;

import com.mitsko.mrdb.entity.Movie;

import java.util.ArrayList;

public interface MovieService {
    void updateRating(String movieID) throws ServiceException;

    float takeAverageRating(String movieName) throws ServiceException;

    void addMovie(String movieName, String imageName, String description) throws ServiceException;

    void updateMovie(String movieName, String newMovieName, String imageName, String description) throws ServiceException;

    void removeMovie(String movieName) throws ServiceException;

    String takeDescription(String movieName) throws ServiceException;

    String takeNameByID(int movieID) throws ServiceException;

    ArrayList<Movie> takeMovies(int offset) throws ServiceException;

    int size() throws ServiceException;
}
