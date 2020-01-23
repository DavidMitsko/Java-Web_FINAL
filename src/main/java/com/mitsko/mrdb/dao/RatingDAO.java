package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.Rating;

public interface RatingDAO {
    void addNewRating(Rating newRating);
    float takeAverageRatingOfMovie(String movieName, String userLogin);
    void updateRating(Rating newRating);
}
