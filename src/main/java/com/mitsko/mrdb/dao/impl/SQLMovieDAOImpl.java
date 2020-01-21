package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.MovieDAO;
import com.mitsko.mrdb.dao.util.ConnectionPool;
import com.mitsko.mrdb.dao.util.Statements;
import com.mitsko.mrdb.entity.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLMovieDAOImpl implements MovieDAO {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void addMovie(Movie movie) {
        Connection connection = connectionPool.getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.ADD_NEW_MOVIE);

            preparedStatement.setString(1, movie.getName());
            preparedStatement.setFloat(2, movie.getAverageRating());
            preparedStatement.setInt(3, movie.getCountOfRatings());

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
    }

    @Override
    public void removeMovie(String movieName) {

    }

    @Override
    public ResultSet takeAllMoviesName() {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.TAKE_ALL_MOVIES_NAME);

            resultSet = preparedStatement.executeQuery();
        }catch (SQLException ex) {

        }
        return resultSet;
    }

    @Override
    public void updateRating(float newRating, String name) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.UPDATE_MOVIES_RATING);

            preparedStatement.setString(1, name);
            preparedStatement.setFloat(2, newRating);

            preparedStatement.executeUpdate();
        } catch(SQLException ex) {

        }
    }

    @Override
    public void updateCountOfRating(float newCountOfRating, String name) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.UPDATE_COUNT_OF_RATING);

            preparedStatement.setString(1, name);
            preparedStatement.setFloat(2, newCountOfRating);

            preparedStatement.executeUpdate();
        } catch(SQLException ex) {

        }
    }
}
