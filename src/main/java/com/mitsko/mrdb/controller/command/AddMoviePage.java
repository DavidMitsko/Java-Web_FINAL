package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.PagesConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddMoviePage implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return PagesConstants.ADD_MOVIE;
    }
}
