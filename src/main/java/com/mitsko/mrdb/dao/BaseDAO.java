package com.mitsko.mrdb.dao;

import java.util.ArrayList;

public interface BaseDAO<T> {
    ArrayList<T> takeAll();
    int takeID(T identifier);
}
