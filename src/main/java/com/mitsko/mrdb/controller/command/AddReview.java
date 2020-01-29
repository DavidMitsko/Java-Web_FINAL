package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.service.ReviewService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddReview implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ReviewService reviewService = serviceFactory.getReviewService();

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        String movieName = (String)session.getAttribute("movieName");
        String review = req.getParameter("usersReview");

        session.removeAttribute("movieName");
        String page = Constants.MAIN;

        try {
            reviewService.addNewReview(user.getLogin(), movieName, review);
        } catch (ServiceException ex) {
            ex.printStackTrace();
            page = Constants.REVIEW;
        }
        return page;
    }
}
