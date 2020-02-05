package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.service.ReviewService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RemoveReview implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ReviewService reviewService = serviceFactory.getReviewService();

        HttpSession session = req.getSession();
        int userID = (int)session.getAttribute("userID");
        int reviewID = Integer.parseInt(req.getParameter("reviewForRemove"));

        try {
            reviewService.removeReview(userID, reviewID);
        } catch (ServiceException ex) {

        }
        return Constants.MAIN;
    }
}
