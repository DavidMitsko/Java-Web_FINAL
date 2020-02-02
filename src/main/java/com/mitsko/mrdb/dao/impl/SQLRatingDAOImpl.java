package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.RatingDAO;
import com.mitsko.mrdb.dao.pool.ConnectionPool;
import com.mitsko.mrdb.dao.pool.ConnectionPoolException;
import com.mitsko.mrdb.entity.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLRatingDAOImpl implements RatingDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final String ADD_NEW_RATING = "INSERT INTO rating (id, userID, movieID, rating) VALUES(NULL,?,?,?)";
    public static final String TAKE_AVERAGE_RATING_OF_MOVIE = "SELECT AVG(rating) FROM rating WHERE movieID = ?";
    public static final String UPDATE_RATING = "UPDATE rating SET rating = ? WHERE movieID = ? AND userID = ?";
    public static final String REMOVE_RATING = "DELETE FROM rating WHERE movieID = ?";
    public static final String TAKE_USERS_RATING_OF_MOVIE = "SELECT rating FROM rating WHERE userID = ? AND movieID = ?";

    @Override
    public void addNewRating(Rating newRating) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_RATING);

            preparedStatement.setInt(1, newRating.getUserID());
            preparedStatement.setInt(2, newRating.getMovieID());
            preparedStatement.setFloat(3, newRating.getRating());

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        } catch (ConnectionPoolException ex) {

        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public float takeAverageRatingOfMovie(int movieID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        float averageRating = -1;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_AVERAGE_RATING_OF_MOVIE);

            preparedStatement.setInt(1, movieID);

            resultSet = preparedStatement.executeQuery();
            connectionPool.releaseConnection(connection);

            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                averageRating = resultSet.getFloat(1);
            }
        } catch (SQLException ex) {

        } catch (ConnectionPoolException ex) {

        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return averageRating;
    }

    @Override
    public void updateRating(Rating newRating) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_RATING);

            preparedStatement.setFloat(1, newRating.getRating());
            preparedStatement.setInt(2, newRating.getMovieID());
            preparedStatement.setInt(3, newRating.getUserID());

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        } catch (ConnectionPoolException ex) {

        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public void removeAllRatings(int movieID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(REMOVE_RATING);

            preparedStatement.setInt(1, movieID);

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        } catch (ConnectionPoolException ex) {

        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public float takeUsersRatingOfMovie(int userID, int movieID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        float rating = -1;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_USERS_RATING_OF_MOVIE);

            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, movieID);

            resultSet = preparedStatement.executeQuery();

            connectionPool.releaseConnection(connection);
            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                rating = resultSet.getFloat(1);
            }

        } catch (SQLException ex) {

        } catch (ConnectionPoolException ex) {

        } finally {
            connectionPool.closeConnection(connection, preparedStatement,resultSet);
        }
        return rating;
    }
}
