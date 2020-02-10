package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.entity.Movie;
import com.mitsko.mrdb.entity.util.Status;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;
import com.mitsko.mrdb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

public class TakeUsers implements Command {
    private UserService userService;

    public TakeUsers() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest req) {
        try {
            ArrayList<String> usersLogin = userService.takeAllLogins();

            HashMap<String, String> usersInfo = compileUsersInfo(usersLogin);
            req.setAttribute("userMap", usersInfo);
        } catch (ServiceException ex) {

        }
        return Constants.ADMIN;
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
