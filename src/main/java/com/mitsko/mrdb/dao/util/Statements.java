package com.mitsko.mrdb.dao.util;

public class Statements {
    public static final String ADD_NEW_USER = "INSERT INTO user (id, login, password, role," +
            "status, averageRating) VALUES(NULL,?,?,?,?,?)";
    public static final String TAKE_ALL_LOGINS = "SELECT login FROM user";
    public static final String SELECT_USERS_LOGIN = "SELECT login FROM user WHERE login = ?";
    public static final String SELECT_USER = "SELECT * FROM user WHERE login = ? AND password = ?";
    public static final String UPDATE_USERS_RATING = "UPDATE user SET averageRating = ? WHERE login = ?";
    public static final String TAKE_USERS_ID = "SELECT id FROM user WHERE login = ?";

    public static final String NEW_STATUS = "UPDATE user SET status = ? WHERE login = ?";
    public static final String NEW_RATING = "UPDATE user SET averageRating = ? WHERE login = ?";

    public static final String ADD_NEW_MOVIE = "INSERT INTO movie(id, name, averageRating, countOfRating) " +
            "VALUES(NULL,?,?,?)";
    public static final String TAKE_ALL_MOVIES_NAME = "SELECT name FROM movie";
    public static final String UPDATE_MOVIES_RATING = "UPDATE movie SET averageRating = ? WHERE name = ?";
    public static final String UPDATE_COUNT_OF_RATING = "UPDATE movie SET countOfRating = ? WHERE name = ?";

    public static final String ADD_NEW_REVIEW = "INSERT INTO review (id, userLogin, movieName, review) VALUES(NULL,?,?,?)";
    public static final String TAKE_ALL_MOVIES_REVIEW = "SELECT userLogin, review FROM review WHERE movieName = ?";

    public static final String ADD_NEW_RATING = "INSERT INTO rating (id, userLogin, movieName, review) VALUES(NULL,?,?,?)";
    public static final String TAKE_AVERAGE_RATING_OF_MOVIE = "SELECT AVG(rating) FROM rating WHERE movieName = ? " +
            "AND userLogin = ?";
    public static final String UPDATE_RATING = "UPDATE rating SET rating = ? WHERE movieName = ? AND userLogin = ?";
}
