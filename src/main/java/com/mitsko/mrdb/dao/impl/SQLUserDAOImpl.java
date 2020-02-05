package com.mitsko.mrdb.dao.impl;

import com.mitsko.mrdb.dao.DAOException;
import com.mitsko.mrdb.dao.UserDAO;
import com.mitsko.mrdb.dao.pool.ConnectionPool;
import com.mitsko.mrdb.dao.pool.ConnectionPoolException;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Status;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLUserDAOImpl implements UserDAO {
    private final static Logger logger = LogManager.getLogger(SQLUserDAOImpl.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String FIND_LOGIN = "SELECT login FROM user WHERE login = ?";
    private static final String TAKE_ALL_USERS_ID = "SELECT id FROM user";
    public static final String ADD_NEW_USER = "INSERT INTO user (id, login, password, role," +
            "status, averageRating) VALUES(NULL,?,?,?,?,?)";
    public static final String TAKE_ALL_LOGINS = "SELECT login FROM user WHERE id != 1";
    public static final String SELECT_USERS_LOGIN = "SELECT password FROM user WHERE login = ?";
    public static final String SELECT_USER = "SELECT * FROM user WHERE login = ? AND password = ?";
    public static final String UPDATE_USERS_RATING = "UPDATE user SET averageRating = ? WHERE id = ?";
    public static final String TAKE_USERS_ID = "SELECT id FROM user WHERE login = ?";
    public static final String TAKE_USERS_RATING = "SELECT averageRating FROM user WHERE id = ?";
    public static final String TAKE_STATUS = "SELECT status FROM user WHERE id = ?";
    public static final String TAKE_USERS_LOGIN = "SELECT login FROM user WHERE id = ?";

    @Override
    public String takePassword(String login) throws DAOException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_USERS_LOGIN);

            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();



            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                return resultSet.getString(1);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public User takeUserByLoginAndPassword(String login, String password) throws DAOException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();


            if (resultSet.isBeforeFirst()) {

                resultSet.next();

                int id = resultSet.getInt(1);
                String role = resultSet.getString(4);
                String status = resultSet.getString(5);
                int averageRating = resultSet.getInt(6);

                return new User(id, login, password, role, status, averageRating);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }

        return null;
    }

    @Override
    public int registration(User newUser) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int id = -1;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_USER);

            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPassword());
            String role = newUser.getRole().toString();
            preparedStatement.setString(3, role);
            String status = newUser.getStatus().toString();
            preparedStatement.setString(4, status);
            preparedStatement.setInt(5, newUser.getAverageRating());

            preparedStatement.executeUpdate();

            id = takeID(newUser.getLogin());


        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
        return id;
    }

    @Override
    public ArrayList<String> takeAllLogins() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> logins = new ArrayList<>();

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_ALL_LOGINS);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                logins.add(resultSet.getString(1));
            }


        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return logins;
    }

    @Override
    public void updateRating(int userID, int newRating) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USERS_RATING);

            preparedStatement.setInt(1, newRating);
            preparedStatement.setInt(2, userID);

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
    public int takeRating(int userID) throws DAOException {
        int rating = -1;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_USERS_RATING);

            preparedStatement.setInt(1, userID);

            resultSet = preparedStatement.executeQuery();


            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                rating = resultSet.getInt(1);
            }


        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return rating;
    }

    @Override
    public Status takeStatus(int userID) throws DAOException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Status status = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_STATUS);

            preparedStatement.setInt(1, userID);

            resultSet = preparedStatement.executeQuery();


            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                String temp = resultSet.getString(1);
                status = Status.valueOf(temp);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return status;
    }

    @Override
    public boolean findLogin(String login) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(FIND_LOGIN);

            preparedStatement.setString(1, login);

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
    public ArrayList<Integer> takeAllUsersID() throws DAOException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Integer> usersID = new ArrayList<>();

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_ALL_USERS_ID);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                usersID.add(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return usersID;
    }

    @Override
    public int takeID(String login) throws DAOException {
        int id = -1;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_USERS_ID);

            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();


            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                id = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return id;
    }

    @Override
    public String takeLogin(int userID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String login = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(TAKE_USERS_LOGIN);

            preparedStatement.setInt(1, userID);

            resultSet = preparedStatement.executeQuery();


            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                login = resultSet.getString(1);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return login;
    }
}
