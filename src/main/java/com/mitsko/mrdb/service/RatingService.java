package com.mitsko.mrdb.service;

import com.mitsko.mrdb.entity.User;

public interface RatingService {
    void addNewRating(User user, String movieName, float rating) throws ServiceException;

    void updateRating(String userLogin, String movieName, float rating) throws ServiceException;
}
