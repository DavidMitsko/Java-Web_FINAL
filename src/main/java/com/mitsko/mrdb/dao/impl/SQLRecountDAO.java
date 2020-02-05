package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.DAOException;
import com.mitsko.mrdb.dao.RecountDAO;
import com.mitsko.mrdb.dao.pool.ConnectionPool;
import com.mitsko.mrdb.dao.pool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLRecountDAO implements RecountDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String ADD_USERS_RATING = "INSERT INTO recount (id, userID, movieID, direct) VALUES(NULL, ?, ?, ?)";
    private static final String FIND_USERS_RATING = "SELECT * FROM recount WHERE userID = ? AND movieID = ?";
    private static final String TAKE_DIRECT = "SELECT direct FROM recount WHERE userID = ? AND movieID = ?";

    @Override
    public void add(int userID, int movieID, int direct) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(ADD_USERS_RATING);

            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, movieID);
            preparedStatement.setInt(3, direct);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public boolean find(int userID, int movieID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(FIND_USERS_RATING);

            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, movieID);

            resultSet = preparedStatement.executeQuery();


            return resultSet.isBeforeFirst();
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int takeDirect(int userID, int movieID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int direct = -2;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_DIRECT);

            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, movieID);

            resultSet = preparedStatement.executeQuery();


            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                direct = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return direct;
    }
}
