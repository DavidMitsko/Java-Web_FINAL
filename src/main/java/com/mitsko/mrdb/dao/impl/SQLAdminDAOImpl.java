package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.AdminDAO;
import com.mitsko.mrdb.dao.util.ConnectionPool;
import com.mitsko.mrdb.dao.util.Statements;
import com.mitsko.mrdb.entity.util.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLAdminDAOImpl implements AdminDAO {
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void refreshStatus(Status newStatus, String login) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.NEW_STATUS);

            String status = newStatus.toString();
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, login);

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        }catch (SQLException ex) {

        }
    }

    @Override
    public void refreshAverageRating(int newRating, String login) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.NEW_RATING);

            preparedStatement.setInt(1, newRating);
            preparedStatement.setString(2, login);

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        }catch (SQLException ex) {

        }
    }
}
