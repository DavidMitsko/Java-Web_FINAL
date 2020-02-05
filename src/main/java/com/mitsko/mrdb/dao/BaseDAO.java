package com.mitsko.mrdb.dao;

import java.util.ArrayList;

public interface BaseDAO<T> {
    ArrayList<T> takeAll() throws DAOException;
    int takeID(T identifier) throws DAOException;
}
