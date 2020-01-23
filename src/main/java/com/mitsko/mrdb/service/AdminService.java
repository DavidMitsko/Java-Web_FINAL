package com.mitsko.mrdb.service;

import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Status;

public interface AdminService {
    void refreshStatus(User user, String userLogin, Status newStatus) throws ServiceException;

    void refreshAverageRating(User user, String userLogin, int newRating) throws ServiceException;
}
