package com.mitsko.mrdb.controller.command.impl;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.CommandException;
import com.mitsko.mrdb.controller.command.util.PagesConstants;
import com.mitsko.mrdb.entity.util.Status;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;
import com.mitsko.mrdb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

public class TakeUsers implements Command {
    private UserService userService;

    public TakeUsers() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            ArrayList<String> usersLogin = userService.takeAllLogins();

            HashMap<String, String> usersInfo = compileUsersInfo(usersLogin);
            req.setAttribute("userMap", usersInfo);
        } catch (ServiceException ex) {
//            req.setAttribute("error", ex.getMessage());
//            return PagesConstants.ERROR;
            throw new CommandException(ex);
        }
        return PagesConstants.ADMIN;
    }

    private HashMap<String, String> compileUsersInfo(ArrayList<String> usersLogins) throws ServiceException {
        HashMap<String, String> usersInfo = new HashMap<>();
        for(String login: usersLogins) {
            Status status = userService.takeStatus(login);
            usersInfo.put(login, status.toString());
        }
        return usersInfo;
    }
}
