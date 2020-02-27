package com.mitsko.mrdb.controller.command.impl;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.CommandException;
import com.mitsko.mrdb.controller.command.util.PagesConstants;
import com.mitsko.mrdb.controller.command.util.RequestsConstants;
import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.entity.util.Status;
import com.mitsko.mrdb.service.AdminService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;
import com.mitsko.mrdb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeStatus implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AdminService adminService = serviceFactory.getAdminService();
        UserService userService = serviceFactory.getUserService();

        HttpSession session = req.getSession();
        Role userRole = (Role)session.getAttribute("role");

        String login = req.getParameter("status");
        try {
            Status oldStatus = userService.takeStatus(login);
            Status newStatus;
            if(oldStatus == Status.NO_LIMITS) {
                newStatus = Status.BAN;
            } else {
                newStatus = Status.NO_LIMITS;
            }
            adminService.refreshStatus(userRole, login, newStatus);
        } catch (ServiceException ex) {
//            req.setAttribute("error", ex.getMessage());
//            return PagesConstants.ERROR;
            throw new CommandException(ex);
        }
        return RequestsConstants.TAKE_USERS;
    }
}
