package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;
import com.mitsko.mrdb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignIn implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String page = Constants.SIGN_IN;

        try {
            User user = userService.signIn(login, password);

            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            if(user.getRole() == Role.ADMIN) {
                page = Constants.ADMIN;
            }
        } catch (ServiceException ex) {

        }
        return page;
    }
}
