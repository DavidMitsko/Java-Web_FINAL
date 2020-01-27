package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.UserDAO;
import com.mitsko.mrdb.dao.util.ConnectionPool;
import com.mitsko.mrdb.dao.util.Statements;
import com.mitsko.mrdb.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLUserDAOImpl implements UserDAO {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public String takePassword(String login) {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.SELECT_USERS_LOGIN);

            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();

            connectionPool.releaseConnection(connection);

            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                return resultSet.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            resultSet.next();

            int id = resultSet.getInt(1);
            String role = resultSet.getString(4);
            String status = resultSet.getString(5);
            int averageRating = resultSet.getInt(6);

            connectionPool.releaseConnection(connection);

            return new User(id, login, password, role, status, averageRating);
        } catch (SQLException ex) {

        }

        return null;
    }

    @Override
    public int registration(User newUser) {
        Connection connection = connectionPool.getConnection();
        int id = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.ADD_NEW_USER);

            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPassword());
            String role = newUser.getRole().toString();
            preparedStatement.setString(3, role);
            String status = newUser.getStatus().toString();
            preparedStatement.setString(4, status);
            preparedStatement.setInt(5, newUser.getAverageRating());

            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(Statements.TAKE_USERS_ID);

            preparedStatement.setString(1, newUser.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            id = resultSet.getInt(1);

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
        return id;
    }

    @Override
    public ArrayList takeAllLogins() {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        ArrayList<String> logins = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.TAKE_ALL_LOGINS);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                logins.add(resultSet.getString(1));
            }

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
        return logins;
    }

    @Override
    public void updateRating(String login, int newRating) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.UPDATE_USERS_RATING);

            preparedStatement.setInt(1, newRating);
            preparedStatement.setString(2, login);

            preparedStatement.executeUpdate();

            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {

        }
    }
}
