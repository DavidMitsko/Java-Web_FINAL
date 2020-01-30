package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.AdminDAO;
import com.mitsko.mrdb.dao.DAOFactory;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.entity.util.Status;
import com.mitsko.mrdb.service.AdminService;
import com.mitsko.mrdb.service.ServiceException;

public class AdminServiceImpl implements AdminService {
    private AdminDAO adminDAO;

    public AdminServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        adminDAO = daoFactory.getSQLAdminDAO();
    }

    @Override
    public void refreshStatus(Role userRole, String userLogin, Status newStatus) throws ServiceException {
        if(userRole == null || userLogin.equals("") || newStatus == null) {
            throw new ServiceException();
        }

        if(userRole != Role.ADMIN) {
            throw new ServiceException("You have low access");
        }

        adminDAO.refreshStatus(newStatus, userLogin);
    }

    @Override
    public void refreshAverageRating(Role userRole, String userLogin, int newRating) throws ServiceException {
        if(userRole == null || userLogin.equals("") || newRating < 0) {
            throw new ServiceException();
        }

        if(userRole != Role.ADMIN) {
            throw new ServiceException("You have low access");
        }

        adminDAO.refreshAverageRating(newRating, userLogin);
    }
}
