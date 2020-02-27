package com.mitsko.mrdb.controller.command.impl;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.util.PagesConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeMoviePage implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String movieName = req.getParameter("movieName");

        req.getSession().setAttribute("movieName", movieName);
        return PagesConstants.CHANGE_MOVIE;
    }
}
