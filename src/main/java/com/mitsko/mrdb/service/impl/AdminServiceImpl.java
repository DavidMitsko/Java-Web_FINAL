package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.AdminDAO;
import com.mitsko.mrdb.dao.DAOException;
import com.mitsko.mrdb.dao.DAOFactory;
import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.entity.util.Status;
import com.mitsko.mrdb.service.AdminService;
import com.mitsko.mrdb.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminServiceImpl implements AdminService {
    private final static Logger logger = LogManager.getLogger(AdminServiceImpl.class);
    private AdminDAO adminDAO;

    public AdminServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        adminDAO = daoFactory.getSQLAdminDAO();
    }

    @Override
    public void refreshStatus(Role userRole, String userLogin, Status newStatus) throws ServiceException {
        if (userRole == null || userLogin.equals("") || newStatus == null) {
            throw new ServiceException();
        }

        if (userRole != Role.ADMIN) {
            throw new ServiceException("You have low access");
        }

        try {
            adminDAO.refreshStatus(newStatus, userLogin);

            logger.debug("Refreshed users status");
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }
}
