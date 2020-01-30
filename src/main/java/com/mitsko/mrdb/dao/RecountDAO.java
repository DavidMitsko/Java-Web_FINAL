package com.mitsko.mrdb.dao;

public interface RecountDAO {
    void add(int userID, int movieID);

    boolean find(int userID, int movieID);
}
