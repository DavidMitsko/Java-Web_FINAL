package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.Review;

import java.util.ArrayList;

public interface ReviewDAO {
    void addReview(Review review) throws DAOException;

    ArrayList<Review> takeAllMoviesReviews(int movieID) throws DAOException;

    void removeReview(int reviewID) throws DAOException;

    void removeAllReviews(int movieID) throws DAOException;

    ArrayList<Review> takeAllUsersReviews(int userID) throws DAOException;

    int takeUsersID(int reviewID) throws DAOException;
}
