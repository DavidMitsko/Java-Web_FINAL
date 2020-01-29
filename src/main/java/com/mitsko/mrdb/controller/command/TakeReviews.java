package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.entity.Review;
import com.mitsko.mrdb.service.ReviewService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class TakeReviews implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ReviewService reviewService = serviceFactory.getReviewService();

        String page = Constants.REVIEW;

        try {
            String movieName = req.getParameter("Review");
            ArrayList<Review> reviewList = reviewService.takeAllReview(movieName);

            req.setAttribute("reviewList", reviewList);

            HttpSession session = req.getSession();
            session.setAttribute("movieName", movieName);
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
        return page;
    }
}
