package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.Rating;

public interface RatingDAO {
    void addNewRating(Rating newRating);

    float takeAverageRatingOfMovie(int movieID);

    void updateRating(Rating newRating);

    void removeAllRatings(int movieID);

    float takeUsersRatingOfMovie(int userID, int movieID);
}
