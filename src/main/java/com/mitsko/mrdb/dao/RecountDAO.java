package com.mitsko.mrdb.dao;

public interface RecountDAO {
    void add(int userID, int movieID, int direct);

    boolean find(int userID, int movieID);

    int takeDirect(int userID, int movieID);
}
