package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.RatingDAO;
import com.mitsko.mrdb.dao.util.ConnectionPool;
import com.mitsko.mrdb.dao.util.Statements;
import com.mitsko.mrdb.entity.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLRatingDAOImpl implements RatingDAO {
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void addNewRating(Rating newRating) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.ADD_NEW_RATING);

            preparedStatement.setString(1, newRating.getUsersLogin());
            preparedStatement.setString(2, newRating.getNameOfMovie());
            preparedStatement.setFloat(3, newRating.getRating());

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
    }

    @Override
    public float takeAverageRatingOfMovie(String movieName, String userLogin) {
        Connection connection = connectionPool.getConnection();
        float averageRating = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.TAKE_AVERAGE_RATING_OF_MOVIE);

            preparedStatement.setString(1, movieName);
            preparedStatement.setString(2, userLogin);

            ResultSet resultSet = preparedStatement.executeQuery();
            connectionPool.releaseConnection(connection);

            averageRating = resultSet.getFloat(1);
        } catch (SQLException ex) {

        }
        return averageRating;
    }

    @Override
    public void updateRating(Rating newRating) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.TAKE_AVERAGE_RATING_OF_MOVIE);

            preparedStatement.setFloat(1, newRating.getRating());
            preparedStatement.setString(2, newRating.getNameOfMovie());
            preparedStatement.setString(3, newRating.getUsersLogin());

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
    }
}
