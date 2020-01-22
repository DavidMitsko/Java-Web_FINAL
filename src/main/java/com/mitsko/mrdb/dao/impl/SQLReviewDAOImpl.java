package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.ReviewDAO;
import com.mitsko.mrdb.dao.util.ConnectionPool;
import com.mitsko.mrdb.dao.util.Statements;
import com.mitsko.mrdb.entity.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public ResultSet takeAllMoviesReviews(String name) {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.TAKE_ALL_MOVIES_REVIEW);

            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
        return resultSet;
    }
}
