package com.mitsko.mrdb.service;

import com.mitsko.mrdb.entity.Review;
import com.mitsko.mrdb.entity.User;

import java.util.ArrayList;

public interface ReviewService {
    void addNewReview(int userID, String movieName, String review) throws ServiceException;

    void removeReview(int userID, int reviewID) throws ServiceException;

    void removeAllMoviesReview(String movieID) throws ServiceException;

    ArrayList<Review> takeAllMoviesReview(String movieName) throws ServiceException;

    ArrayList<Review> takeAllUsersReview(int userID) throws ServiceException;
}
