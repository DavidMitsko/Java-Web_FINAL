package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.UserDAO;
import com.mitsko.mrdb.dao.util.ConnectionPool;
import com.mitsko.mrdb.dao.util.Statements;
import com.mitsko.mrdb.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDAOImpl implements UserDAO {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public String takeLogin(String login) {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.SELECT_USERS_LOGIN);

            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();

            connectionPool.releaseConnection(connection);

            if (resultSet.isBeforeFirst()) {
                return resultSet.getString(1);
            }
        } catch (SQLException ex) {

        }
        return null;
    }

    @Override
    public User signIn(String login, String password) {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.SELECT_USER);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            int id = resultSet.getInt(1);
            String role = resultSet.getString(4);
            String status = resultSet.getString(5);
            float averageRating = resultSet.getFloat(6);

            connectionPool.releaseConnection(connection);

            return new User(id, login, password, role, status, averageRating);
        } catch (SQLException ex) {

        }

        return null;
    }

    @Override
    public void registration(User newUser) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.ADD_NEW_USER_STATEMENT);

            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPassword());

            String role = newUser.getRole().toString();
            preparedStatement.setString(3, role);

            String status = newUser.getStatus().toString();
            preparedStatement.setString(4, status);

            preparedStatement.setFloat(5, newUser.getAverageRating());

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);

        } catch (SQLException ex) {

        }
    }

    @Override
    public ResultSet takeAllLogins() {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.TAKE_ALL_LOGINS);

            resultSet = preparedStatement.executeQuery();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
        return resultSet;
    }
}
