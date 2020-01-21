package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.Movie;

import java.sql.ResultSet;

public interface MovieDAO {
    void addMovie(Movie movie);
    void removeMovie(String movieName);
    ResultSet takeAllMoviesName();
    void updateRating(float newRating, String name);
    void updateCountOfRating(float newCountOfRating, String name);
}
