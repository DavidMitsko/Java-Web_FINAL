package com.mitsko.mrdb.controller.command.impl;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.CommandException;
import com.mitsko.mrdb.controller.command.util.PagesConstants;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;
import com.mitsko.mrdb.service.UserException;
import com.mitsko.mrdb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Registration implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String page = PagesConstants.REGISTRATION;
        try {
            User user = userService.registration(login, password);

            HttpSession session = req.getSession();
            session.setAttribute("userID", user.getID());
            session.setAttribute("role", user.getRole());
            session.setAttribute("status", user.getStatus());

            if(user != null) {
                page = PagesConstants.MAIN;
            }
        } catch (ServiceException ex) {
            throw new CommandException(ex);
        }
        return page;
    }
}
