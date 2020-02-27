package com.mitsko.mrdb.controller.command.impl;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.util.PagesConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Rating implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String movieName = req.getParameter("movieNameForRating");

        HttpSession session = req.getSession();
        if(movieName != null) {
            session.setAttribute("movieName", movieName);
        }

        String message = (String)session.getAttribute("message");
        if(message != null) {
            req.setAttribute("message", message);
            session.removeAttribute("message");
        }

        return PagesConstants.RATING;
    }
}
