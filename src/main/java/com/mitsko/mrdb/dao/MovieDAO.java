package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.Movie;

import java.util.ArrayList;

public interface MovieDAO {
    void addMovie(Movie movie);

    void removeMovie(String movieName);

    ArrayList<Movie> takeAllMovies();

    void updateRating(float newRating, int movieID);

    void updateCountOfRating(int newCountOfRating, int movieID);

    int takeCountOfRating(int movieID);

    float takeRatingOfMovie(int movieID);

    int takeID(String movieName);
}
