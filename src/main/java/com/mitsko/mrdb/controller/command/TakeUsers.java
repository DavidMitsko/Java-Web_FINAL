package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;
import com.mitsko.mrdb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class TakeUsers implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            ArrayList<String> usersLogin = userService.takeAllLogins();

            req.setAttribute("userList", usersLogin);
        } catch (ServiceException ex) {

        }
        return Constants.ADMIN;
    }
}
