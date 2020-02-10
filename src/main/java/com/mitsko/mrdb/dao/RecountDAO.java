package com.mitsko.mrdb.dao;

public interface RecountDAO {
    void add(int userID, int movieID, int direct) throws DAOException;

    boolean find(int userID, int movieID) throws DAOException;

    int takeDirect(int userID, int movieID) throws DAOException;

    void removeRecount(int userID, int movieID) throws DAOException;
}
