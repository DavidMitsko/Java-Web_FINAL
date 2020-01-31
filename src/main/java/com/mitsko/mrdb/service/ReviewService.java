package com.mitsko.mrdb.service;

import com.mitsko.mrdb.entity.Review;
import com.mitsko.mrdb.entity.User;

import java.util.ArrayList;

public interface ReviewService {
    void addNewReview(int userID, String movieName, String review) throws ServiceException;

    void removeReview(User user, int userID, String movieName) throws ServiceException;

    void removeAllMoviesReview(String movieID) throws ServiceException;

    ArrayList<Review> takeAllReview(String movieName) throws ServiceException;
}
