package com.mitsko.mrdb.service;

import com.mitsko.mrdb.entity.Review;
import com.mitsko.mrdb.entity.User;

import java.util.ArrayList;

public interface ReviewService {
    void addNewReview(String userLogin, String movieName, String review) throws ServiceException;

    void removeReview(User user, String userLogin, String movieName) throws ServiceException;

    ArrayList<Review> takeAllReview(String movieName) throws ServiceException;
}