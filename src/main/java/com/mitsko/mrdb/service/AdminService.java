package com.mitsko.mrdb.service;

import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.entity.util.Status;

public interface AdminService {
    void refreshStatus(Role userRole, String userLogin, Status newStatus) throws ServiceException;
}
