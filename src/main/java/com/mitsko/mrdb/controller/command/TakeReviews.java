package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.entity.Review;
import com.mitsko.mrdb.service.ReviewService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;
import com.mitsko.mrdb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

public class TakeReviews implements Command {
    private ServiceFactory serviceFactory;
    private ReviewService reviewService;
    private UserService userService;

    public TakeReviews() {
        serviceFactory = ServiceFactory.getInstance();
        reviewService = serviceFactory.getReviewService();
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = Constants.REVIEW;

        try {
            String movieName = req.getParameter("Review");
            ArrayList<Review> reviewList = reviewService.takeAllReview(movieName);

            req.setAttribute("reviewList", takeLogins(reviewList));

            HttpSession session = req.getSession();
            session.setAttribute("movieName", movieName);
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
        return page;
    }

    private HashMap<String, Review> takeLogins(ArrayList<Review> reviewList) {
        HashMap<String, Review> usersLoginMap = new HashMap();
        try {
            for (Review review : reviewList) {
                String login = userService.takeLogin(review.getUserID());
                usersLoginMap.put(login, review);
            }
        } catch (ServiceException ex) {

        }
        return usersLoginMap;
    }
}
