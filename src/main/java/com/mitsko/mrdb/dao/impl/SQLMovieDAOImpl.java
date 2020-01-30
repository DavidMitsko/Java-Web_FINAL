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
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String TAKE_ID = "SELECT id FROM movie WHERE name = ?";
    public static final String ADD_NEW_MOVIE = "INSERT INTO movie(id, name, averageRating, countOfRatings) " +
            "VALUES(NULL,?,?,?)";
    public static final String TAKE_ALL_MOVIES = "SELECT * FROM movie";
    public static final String UPDATE_MOVIES_RATING = "UPDATE movie SET averageRating = ? WHERE id = ?";
    public static final String UPDATE_COUNT_OF_RATING = "UPDATE movie SET countOfRatings = ? WHERE id = ?";
    public static final String TAKE_COUNT_OF_RATING = "SELECT countOfRatings FROM movie WHERE id = ?";
    public static final String TAKE_RATING_OF_MOVIE = "SELECT averageRating FROM movie WHERE id = ?";

    @Override
    public void addMovie(Movie movie) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_MOVIE);

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
            PreparedStatement preparedStatement = connection.prepareStatement(TAKE_ALL_MOVIES);

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
    public void updateRating(float newRating, int movieID) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MOVIES_RATING);

            preparedStatement.setInt(2, movieID);
            preparedStatement.setFloat(1, newRating);

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
    }

    @Override
    public void updateCountOfRating(int newCountOfRating, int movieID) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COUNT_OF_RATING);

            preparedStatement.setInt(2, movieID);
            preparedStatement.setInt(1, newCountOfRating);

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int takeCountOfRating(int movieID) {
        Connection connection = connectionPool.getConnection();
        int count = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(TAKE_COUNT_OF_RATING);

            preparedStatement.setInt(1, movieID);
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
    public float takeRatingOfMovie(int movieId) {
        Connection connection = connectionPool.getConnection();
        float rating = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(TAKE_RATING_OF_MOVIE);

            preparedStatement.setInt(1, movieId);
            ResultSet resultSet = preparedStatement.executeQuery();

            connectionPool.releaseConnection(connection);

            resultSet.next();
            rating = resultSet.getFloat(1);
        } catch (SQLException ex) {

        }
        return rating;
    }

    @Override
    public int takeID(String movieName) {
        Connection connection = connectionPool.getConnection();
        int id = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(TAKE_ID);

            preparedStatement.setString(1, movieName);
            ResultSet resultSet = preparedStatement.executeQuery();

            connectionPool.releaseConnection(connection);

            resultSet.next();
            id = resultSet.getInt(1);
        } catch (SQLException ex) {

        }
        return id;
    }
}
