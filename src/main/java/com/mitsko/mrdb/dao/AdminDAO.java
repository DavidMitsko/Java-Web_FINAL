package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.entity.util.Status;

public interface AdminDAO {
    void refreshStatus(Status newStatus, String login);

    void refreshAverageRating(int newRating, String login);
}
