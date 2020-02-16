package com.mitsko.mrdb.service;

public interface RatingService {
    boolean addNewRating(int userID, String movieName, float rating) throws ServiceException;

    void removeAllMoviesRatings(String movieName) throws ServiceException;
}
