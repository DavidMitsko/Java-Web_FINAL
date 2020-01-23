package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.Review;

import java.util.ArrayList;

public interface ReviewDAO {
    void addReview(Review review);

    ArrayList<Review> takeAllMoviesReviews(String movieName);

    void removeReview(String userLogin, String movieName);

}
