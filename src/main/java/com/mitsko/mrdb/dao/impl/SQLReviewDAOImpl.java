package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.DAOException;
import com.mitsko.mrdb.dao.ReviewDAO;
import com.mitsko.mrdb.dao.pool.ConnectionPool;
import com.mitsko.mrdb.dao.pool.ConnectionPoolException;
import com.mitsko.mrdb.entity.Review;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLReviewDAOImpl implements ReviewDAO {
    private final static Logger logger = LogManager.getLogger(SQLReviewDAOImpl.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String ADD_NEW_REVIEW = "INSERT INTO review (id, userID, movieID, review) VALUES(NULL,?,?,?)";
    private static final String TAKE_ALL_MOVIES_REVIEW = "SELECT userID, review FROM review WHERE movieID = ?";
    private static final String REMOVE_REVIEW = "DELETE FROM review WHERE ID = ?";
    private static final String REMOVE_ALL_REVIEW = "DELETE  FROM review WHERE movieID = ?";
    private static final String TAKE_ALL_USERS_REVIEW = "SELECT * FROM review WHERE userID = ?";
    private static final String TAKE_REVIEW = "SELECT * FROM review WHERE id = ?";

    @Override
    public void addReview(Review review) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_REVIEW);

            preparedStatement.setInt(1, review.getUserID());
            preparedStatement.setInt(2, review.getMovieID());
            preparedStatement.setString(3, review.getReview());

            preparedStatement.executeUpdate();

            logger.debug("Added new review to movie in db");
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
    public ArrayList<Review> takeAllMoviesReviews(int movieID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Review> reviewList = new ArrayList<>();

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_ALL_MOVIES_REVIEW);

            preparedStatement.setInt(1, movieID);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userID = resultSet.getInt(1);
                String review = resultSet.getString(2);

                Review oldReview = new Review(userID, movieID, review);
                reviewList.add(oldReview);
            }

            logger.debug("From db received all movies reviews");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return reviewList;
    }

    @Override
    public void removeReview(int reviewID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(REMOVE_REVIEW);

            preparedStatement.setInt(1, reviewID);

            preparedStatement.executeUpdate();

            logger.debug("From db removed movies review");
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
    public void removeAllReviews(int movieID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(REMOVE_ALL_REVIEW);

            preparedStatement.setInt(1, movieID);

            preparedStatement.executeUpdate();

            logger.debug("From db removed all movies reviews");
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
    public ArrayList<Review> takeAllUsersReviews(int userID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Review> reviewList = new ArrayList<>();

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_ALL_USERS_REVIEW);

            preparedStatement.setInt(1, userID);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt(1);
                int movieID = resultSet.getInt(3);
                String review = resultSet.getString(4);

                Review oldReview = new Review(ID, userID, movieID, review);
                reviewList.add(oldReview);
            }

            logger.debug("From db received all users reviews");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return reviewList;
    }

    @Override
    public Review takeByID(int reviewID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Review reviewFromDB = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_REVIEW);

            preparedStatement.setInt(1, reviewID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                resultSet.next();

                int id = resultSet.getInt(1);
                int userID = resultSet.getInt(2);
                int movieID = resultSet.getInt(3);
                String review = resultSet.getString(3);

                reviewFromDB = new Review(id, userID, movieID, review);
            }

            logger.debug("From db received review bi ID");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return reviewFromDB;
    }
}
