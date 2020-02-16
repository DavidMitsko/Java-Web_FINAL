package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.Movie;

import java.util.ArrayList;

public interface MovieDAO {
    void addMovie(Movie movie) throws DAOException;

    void removeMovie(String movieName) throws DAOException;

    void updateRating(float newRating, int movieID) throws DAOException;

    void updateCountOfRating(int newCountOfRating, int movieID) throws DAOException;

    int takeCountOfRating(int movieID) throws DAOException;

    float takeRatingOfMovie(int movieID) throws DAOException;

    int takeID(String movieName) throws DAOException;

    String takeDescription(int movieID) throws DAOException;

    String takeName(int movieID) throws DAOException;

    ArrayList<Movie> takeMovies(int offset) throws DAOException;

    int size() throws DAOException;
}
