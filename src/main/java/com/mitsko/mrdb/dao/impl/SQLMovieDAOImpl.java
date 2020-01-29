package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.MovieDAO;
import com.mitsko.mrdb.dao.util.ConnectionPool;
import com.mitsko.mrdb.dao.util.Statements;
import com.mitsko.mrdb.entity.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLMovieDAOImpl implements MovieDAO {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void addMovie(Movie movie) {
        Connection connection = connectionPool.getConnection();

        try {
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
    public ArrayList<Movie> takeAllMovies() {
        Connection connection = connectionPool.getConnection();
        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.TAKE_ALL_MOVIES_NAME);

            ResultSet resultSet = preparedStatement.executeQuery();

            connectionPool.releaseConnection(connection);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                float averageRating = resultSet.getFloat(3);
                int countOfRatings = resultSet.getInt(4);
                Movie film = new Movie(id, name, averageRating, countOfRatings);

                movieList.add(film);
            }
        } catch (SQLException ex) {

        }
        return movieList;
    }

    @Override
    public void updateRating(float newRating, String name) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.UPDATE_MOVIES_RATING);

            preparedStatement.setString(2, name);
            preparedStatement.setFloat(1, newRating);

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
    }

    @Override
    public void updateCountOfRating(int newCountOfRating, String name) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.UPDATE_COUNT_OF_RATING);

            preparedStatement.setString(2, name);
            preparedStatement.setInt(1, newCountOfRating);

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int takeCountOfRating(String name) {
        Connection connection = connectionPool.getConnection();
        int count = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.TAKE_COUNT_OF_RATING);

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            connectionPool.releaseConnection(connection);

            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    @Override
    public float takeRatingOfMovie(String name) {
        Connection connection = connectionPool.getConnection();
        float rating = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.TAKE_RATING_OF_MOVIE);

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            connectionPool.releaseConnection(connection);

            resultSet.next();
            rating = resultSet.getFloat(1);
        } catch (SQLException ex) {

        }
        return rating;
    }
}
