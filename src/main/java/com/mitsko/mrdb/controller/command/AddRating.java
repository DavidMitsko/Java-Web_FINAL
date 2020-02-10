package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.service.RatingService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddRating implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RatingService ratingService = serviceFactory.getRatingService();

        String page = Constants.MAIN;

        HttpSession session =  req.getSession();
        String movieName = (String)session.getAttribute("movieName");
        int userID = (int)session.getAttribute("userID");
        float newRating = Float.parseFloat(req.getParameter("newRating"));

        try {
            ratingService.addNewRating(userID, movieName, newRating);
        } catch (ServiceException ex) {

        }
        return page;
    }
}
