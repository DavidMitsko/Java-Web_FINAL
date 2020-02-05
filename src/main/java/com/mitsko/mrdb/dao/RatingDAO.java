package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.Rating;

public interface RatingDAO {
    void addNewRating(Rating newRating) throws DAOException;

    float takeAverageRatingOfMovie(int movieID) throws DAOException;

    void updateRating(Rating newRating) throws DAOException;

    void removeAllRatings(int movieID) throws DAOException;

    float takeUsersRatingOfMovie(int userID, int movieID) throws DAOException;
}
