package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.Movie;

import java.util.ArrayList;

public interface MovieDAO {
    void addMovie(Movie movie);
    void removeMovie(String movieName);
    ArrayList<Movie> takeAllMovies();
    void updateRating(float newRating, String name);
    void updateCountOfRating(int newCountOfRating, String name);
    int takeCountOfRating(String name);
    float takeRatingOfMovie(String name);
}
