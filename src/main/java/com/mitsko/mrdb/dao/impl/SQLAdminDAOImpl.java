package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.AdminDAO;
import com.mitsko.mrdb.dao.DAOException;
import com.mitsko.mrdb.dao.pool.ConnectionPool;
import com.mitsko.mrdb.dao.pool.ConnectionPoolException;
import com.mitsko.mrdb.entity.util.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLAdminDAOImpl implements AdminDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final String NEW_STATUS = "UPDATE user SET status = ? WHERE login = ?";
    public static final String NEW_RATING = "UPDATE user SET averageRating = ? WHERE login = ?";

    @Override
    public void refreshStatus(Status newStatus, String login) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(NEW_STATUS);

            String status = newStatus.toString();
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, login);

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
    public void refreshAverageRating(int newRating, String login) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(NEW_RATING);

            preparedStatement.setInt(1, newRating);
            preparedStatement.setString(2, login);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }
}
