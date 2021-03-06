package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.DAOException;
import com.mitsko.mrdb.dao.MovieDAO;
import com.mitsko.mrdb.dao.pool.ConnectionPool;
import com.mitsko.mrdb.dao.pool.ConnectionPoolException;
import com.mitsko.mrdb.entity.Movie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLMovieDAOImpl implements MovieDAO {
    private final static Logger logger = LogManager.getLogger(SQLMovieDAOImpl.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String TAKE_ID = "SELECT id FROM movie WHERE name = ?";
    private static final String TAKE_MOVIE = "SELECT * FROM movie WHERE id = ?";
    private static final String ADD_NEW_MOVIE = "INSERT INTO movie(id, name, averageRating, countOfRatings," +
            " imageName, description) VALUES(NULL,?,?,?,?,?)";
    private static final String UPDATE_MOVIE = "UPDATE movie SET name = ?, averageRating = ?," +
            " countOfRatings = ?, imageName = ?, description = ? WHERE id = ?";
    private static final String UPDATE_MOVIES_RATING = "UPDATE movie SET averageRating = ? WHERE id = ?";
    private static final String UPDATE_COUNT_OF_RATING = "UPDATE movie SET countOfRatings = ? WHERE id = ?";
    private static final String TAKE_COUNT_OF_RATING = "SELECT countOfRatings FROM movie WHERE id = ?";
    private static final String TAKE_RATING_OF_MOVIE = "SELECT averageRating FROM movie WHERE id = ?";
    private static final String REMOVE_MOVIE = "DELETE FROM movie WHERE name = ?";
    private static final String TAKE_DESCRIPTION = "SELECT description FROM movie WHERE id = ?";
    private static final String TAKE_NAME = "SELECT name FROM movie WHERE id = ?";
    private static final String TAKE_IMAGE_NAME = "SELECT imageName FROM movie WHERE id = ?";
    private static final String TAKE_MOVIES = "SELECT * FROM movie LIMIT 3 OFFSET ?";
    private static final String TAKE_SIZE = "SELECT COUNT(*) FROM movie";

    @Override
    public void addMovie(Movie movie) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_MOVIE);

            preparedStatement.setString(1, movie.getName());
            preparedStatement.setFloat(2, movie.getAverageRating());
            preparedStatement.setInt(3, movie.getCountOfRatings());
            preparedStatement.setString(4, movie.getImageName());
            preparedStatement.setString(5, movie.getDescription());

            preparedStatement.executeUpdate();

            logger.debug("Added a " + movie.getName() + " in db");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public void updateMovie(Movie movie) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_MOVIE);

            preparedStatement.setString(1, movie.getName());
            preparedStatement.setFloat(2, movie.getAverageRating());
            preparedStatement.setInt(3, movie.getCountOfRatings());
            preparedStatement.setString(4, movie.getImageName());
            preparedStatement.setString(5, movie.getDescription());

            preparedStatement.setInt(6, movie.getID());

            preparedStatement.executeUpdate();

            logger.debug("Updated a " + movie.getName() + " in db");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public void removeMovie(String movieName) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(REMOVE_MOVIE);

            preparedStatement.setString(1, movieName);
            preparedStatement.executeUpdate();

            logger.debug(movieName + " removed from db");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }


    @Override
    public Movie takeMovie(int movieID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_MOVIE);

            preparedStatement.setInt(1, movieID);

            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return compileMovie(resultSet);
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public ArrayList<Movie> takeMovies(int offset) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Movie> movieList = new ArrayList<>();

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_MOVIES);

            preparedStatement.setInt(1, offset);

            resultSet = preparedStatement.executeQuery();

            compileList(resultSet, movieList);
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return movieList;
    }

    private Movie compileMovie(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        float averageRating = resultSet.getFloat(3);
        int countOfRatings = resultSet.getInt(4);
        String imageName = resultSet.getString(5);
        String description = resultSet.getString(6);

        return new Movie(id, name, averageRating, countOfRatings, imageName, description);
    }

    private void compileList(ResultSet resultSet, ArrayList<Movie> movieList) throws SQLException {
        while (resultSet.next()) {
            movieList.add(compileMovie(resultSet));
        }

        logger.debug("Tacked movies from db");
    }

    @Override
    public int size() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int size = -1;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_SIZE);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                size = resultSet.getInt(1);
            }

            logger.debug("Received quantity of movies in db");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return size;
    }

    @Override
    public void updateRating(float newRating, int movieID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_MOVIES_RATING);

            preparedStatement.setInt(2, movieID);
            preparedStatement.setFloat(1, newRating);

            preparedStatement.executeUpdate();

            logger.debug("Movie with " + movieID + " id have new rating");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public void updateCountOfRating(int newCountOfRating, int movieID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COUNT_OF_RATING);

            preparedStatement.setInt(2, movieID);
            preparedStatement.setInt(1, newCountOfRating);

            preparedStatement.executeUpdate();

            logger.debug("Movie with " + movieID + " id, have new count of ratings");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public int takeCountOfRating(int movieID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = -1;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_COUNT_OF_RATING);

            preparedStatement.setInt(1, movieID);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                count = resultSet.getInt(1);
            }

            logger.debug("From db received movies count of ratings");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public float takeRatingOfMovie(int movieId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        float rating = -1;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_RATING_OF_MOVIE);

            preparedStatement.setInt(1, movieId);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                rating = resultSet.getFloat(1);
            }

            logger.debug("From db received rating of movie");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return rating;
    }

    @Override
    public int takeID(String movieName) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_ID);

            preparedStatement.setString(1, movieName);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                id = resultSet.getInt(1);
            }

            logger.debug("From db received " + movieName + "s ID");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return id;
    }

    @Override
    public String takeDescription(int movieID) throws DAOException {
        return getString(movieID, TAKE_DESCRIPTION);
    }

    @Override
    public String takeName(int movieID) throws DAOException {
        return getString(movieID, TAKE_NAME);
    }

    @Override
    public String takeImageName(int movieID) throws DAOException {
        return getString(movieID, TAKE_IMAGE_NAME);
    }

    private String getString(int movieID, String takeParameter) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String name = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(takeParameter);

            preparedStatement.setInt(1, movieID);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                name = resultSet.getString(1);
            }

            logger.debug("From db received parameter of movie with id " + movieID);
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return name;
    }
}
