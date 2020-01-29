package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.ReviewDAO;
import com.mitsko.mrdb.dao.util.ConnectionPool;
import com.mitsko.mrdb.dao.util.Statements;
import com.mitsko.mrdb.entity.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLReviewDAOImpl implements ReviewDAO {
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void addReview(Review review) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.ADD_NEW_REVIEW);

            preparedStatement.setString(1, review.getUserLogin());
            preparedStatement.setString(2, review.getMovieName());
            preparedStatement.setString(3, review.getReview());

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
    }

    @Override
    public ArrayList<Review> takeAllMoviesReviews(String name) {
        Connection connection = connectionPool.getConnection();
        ArrayList<Review> reviewList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.TAKE_ALL_MOVIES_REVIEW);

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            connectionPool.releaseConnection(connection);

            while (resultSet.next()) {
                //int id = resultSet.getInt(1);
                String userLogin = resultSet.getString(1);
                //String movieName = resultSet.getString(3);
                String review = resultSet.getString(2);

                Review oldReview = new Review(userLogin, name, review);
                reviewList.add(oldReview);
            }
        } catch (SQLException ex) {

        }
        return reviewList;
    }

    @Override
    public void removeReview(String userLogin, String movieName) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.REMOVE_REVIEW);

            preparedStatement.setString(1, userLogin);
            preparedStatement.setString(2, movieName);

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
    }
}
