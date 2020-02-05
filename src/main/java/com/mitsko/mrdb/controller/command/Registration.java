package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
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

        String page = Constants.REGISTRATION;
        try {
            User user = userService.registration(login, password);

            HttpSession session = req.getSession();
            session.setAttribute("userID", user.getID());
            session.setAttribute("role", user.getRole());
            session.setAttribute("status", user.getStatus());

            if(user != null) {
                page = Constants.MAIN;
            }
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
        return page;
    }
}
