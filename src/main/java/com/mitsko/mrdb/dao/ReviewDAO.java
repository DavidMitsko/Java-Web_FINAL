package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.Review;

import java.util.ArrayList;

public interface ReviewDAO {
    void addReview(Review review);

    ArrayList<Review> takeAllMoviesReviews(int movieID);

    void removeReview(int userID, int movieID);

    void removeAllReviews(int movieID);
}
