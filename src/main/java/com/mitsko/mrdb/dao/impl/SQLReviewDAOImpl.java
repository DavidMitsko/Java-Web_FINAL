package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.ReviewDAO;
import com.mitsko.mrdb.dao.util.ConnectionPool;
import com.mitsko.mrdb.entity.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLReviewDAOImpl implements ReviewDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String ADD_NEW_REVIEW = "INSERT INTO review (id, userID, movieID, review) VALUES(NULL,?,?,?)";
    private static final String TAKE_ALL_MOVIES_REVIEW = "SELECT userID, review FROM review WHERE movieID = ?";
    public static final String REMOVE_REVIEW = "DELETE review WHERE userID = ? AND movieID = ?";

    @Override
    public void addReview(Review review) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_REVIEW);

            preparedStatement.setInt(1, review.getUserID());
            preparedStatement.setInt(2, review.getMovieID());
            preparedStatement.setString(3, review.getReview());

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
    }

    @Override
    public ArrayList<Review> takeAllMoviesReviews(int movieID) {
        Connection connection = connectionPool.getConnection();
        ArrayList<Review> reviewList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(TAKE_ALL_MOVIES_REVIEW);

            preparedStatement.setInt(1, movieID);

            ResultSet resultSet = preparedStatement.executeQuery();

            connectionPool.releaseConnection(connection);

            while (resultSet.next()) {
                //int id = resultSet.getInt(1);
                int userID = resultSet.getInt(1);
                //String movieName = resultSet.getString(3);
                String review = resultSet.getString(2);

                Review oldReview = new Review(userID, movieID, review);
                reviewList.add(oldReview);
            }
        } catch (SQLException ex) {

        }
        return reviewList;
    }

    @Override
    public void removeReview(int userID, int movieID) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_REVIEW);

            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, movieID);

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
    }
}
