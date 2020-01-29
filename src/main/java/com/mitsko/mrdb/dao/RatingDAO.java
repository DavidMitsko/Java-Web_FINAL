package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.Rating;

public interface RatingDAO {
    void addNewRating(Rating newRating);

    float takeAverageRatingOfMovie(String movieName);

    void updateRating(Rating newRating);

    void removeRating(String userLogin, String movieName);

    float takeUsersRatingOfMovie(String userLogin, String movieName);
}
