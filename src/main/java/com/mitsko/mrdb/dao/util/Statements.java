package com.mitsko.mrdb.dao.util;

public class Statements {
    public static final String ADD_NEW_USER_STATEMENT = "INSERT INTO user (id, login, password, role," +
            " status, averageRating) VALUES(NULL,?,?,?,?,?)";
    public static final String TAKE_ALL_LOGINS = "SELECT login FROM user";
    public static final String SELECT_USERS_LOGIN = "SELECT login FROM user WHERE login = ?";
    public static final String SELECT_USER = "SELECT * FROM user WHERE login = ? AND password = ?";
}
