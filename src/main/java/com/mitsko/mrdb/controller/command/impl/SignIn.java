package com.mitsko.mrdb.controller.command.impl;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.CommandException;
import com.mitsko.mrdb.controller.command.util.PagesConstants;
import com.mitsko.mrdb.controller.command.util.RequestsConstants;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;
import com.mitsko.mrdb.service.UserException;
import com.mitsko.mrdb.service.UserService;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignIn implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String page = null;

        try {
            User user = userService.signIn(login, password);

            HttpSession session = req.getSession();

            if (user != null) {
                if (user.getRole() == Role.ADMIN) {
                    page = RequestsConstants.TAKE_USERS;
                } else {
                    page = RequestsConstants.TAKE_MOVIE;
                }

                session.setAttribute("userID", user.getID());
                session.setAttribute("role", user.getRole());
                session.setAttribute("status", user.getStatus());
            } else {
                throw new CommandException("Error");
            }
        } catch (ServiceException ex) {
//            if (ex.getClass() == UserException.class) {//ex.getMessage().equals("Wrong password")) {
//                req.setAttribute("login", login);
//                req.setAttribute("message", ex.getMessage());
//                page = PagesConstants.SIGN_IN;
//            } else {
//
//                req.setAttribute("error", ex.getMessage());
//                page = PagesConstants.ERROR;
//            }
            throw new CommandException(ex);
        }
        return page;
    }
}
