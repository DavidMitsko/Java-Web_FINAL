package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;
import com.mitsko.mrdb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Registration implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = userService.registration(login, password);

            HttpSession session = req.getSession();
            session.setAttribute("user", user);
        } catch (ServiceException ex) {

        }
        return "registration";
    }
}
