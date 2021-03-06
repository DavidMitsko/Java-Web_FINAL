package com.mitsko.mrdb.controller.command.impl;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.CommandException;
import com.mitsko.mrdb.controller.command.util.PagesConstants;
import com.mitsko.mrdb.controller.command.util.RequestsConstants;
import com.mitsko.mrdb.resource.ResourceManager;
import com.mitsko.mrdb.service.RatingService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddRating implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RatingService ratingService = serviceFactory.getRatingService();

        String page = RequestsConstants.TAKE_MOVIE;

        HttpSession session = req.getSession();
        String movieName = (String) session.getAttribute("movieName");
        session.removeAttribute("movieName");

        int userID = (int) session.getAttribute("userID");
        float newRating = Float.parseFloat(req.getParameter("newRating"));

        try {
            boolean flag = ratingService.addNewRating(userID, movieName, newRating);

            if (!flag) {
                ResourceManager manager = ResourceManager.getInstance();

                page = RequestsConstants.RATING;
                session.setAttribute("message", manager.getString("error.alreadyRated.message"));
                session.setAttribute("movieName", movieName);
            }
        } catch (ServiceException ex) {
//            req.setAttribute("error", ex.getMessage());
//            return PagesConstants.ERROR;
            throw new CommandException(ex);
        }
        return page;
    }
}
