package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Rating implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String movieName = req.getParameter("movieNameForRating");

        HttpSession session = req.getSession();
        session.setAttribute("movieName", movieName);

        return Constants.RATING;
    }
}
