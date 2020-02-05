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
    private int begin;

    public TakeUsers() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest req) {
        try {
            ArrayList<String> usersLogin = userService.takeAllLogins();

            usersLogin = compileList(usersLogin, req);

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

    private ArrayList<String> compileList(ArrayList<String> usersLogins, HttpServletRequest req) {
        if (req.getServletPath().equals("/Next") && begin + 1 <= usersLogins.size()) {
            begin += 3;
        }
        if (req.getServletPath().equals("/Previous")) {
            begin -= 3;
            if(begin < 0) {
                begin = 0;
            }
        }
        if (req.getServletPath().equals("/Take_Movies")) {
            begin = 0;
        }

        ArrayList<String> userList = new ArrayList<>();
        for (int i = begin; i < begin + 3; i++) {
            if(i >= usersLogins.size()) {
                break;
            }
            userList.add(usersLogins.get(i));
        }
        return userList;
    }
}
