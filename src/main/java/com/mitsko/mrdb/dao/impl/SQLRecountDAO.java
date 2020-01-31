package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.RecountDAO;
import com.mitsko.mrdb.dao.util.ConnectionPool;

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
    public void add(int userID, int movieID, int direct) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USERS_RATING);

            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, movieID);
            preparedStatement.setInt(3, direct);

            preparedStatement.executeUpdate();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
    }

    @Override
    public boolean find(int userID, int movieID) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USERS_RATING);

            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, movieID);

            ResultSet resultSet = preparedStatement.executeQuery();
            connectionPool.releaseConnection(connection);

            return resultSet.isBeforeFirst();
        } catch (SQLException ex) {

        }
        return false;
    }

    @Override
    public int takeDirect(int userID, int movieID) {
        Connection connection = connectionPool.getConnection();
        int direct = -2;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(TAKE_DIRECT);

            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, movieID);

            ResultSet resultSet = preparedStatement.executeQuery();
            connectionPool.releaseConnection(connection);

            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                direct = resultSet.getInt(1);
            }
        } catch (SQLException ex) {

        }
        return direct;
    }
}
