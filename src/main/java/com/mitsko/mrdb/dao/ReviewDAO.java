package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.Review;

import java.sql.ResultSet;

public interface ReviewDAO {
    void addReview(Review review);
    ResultSet takeAllMoviesReviews(String name);
}
