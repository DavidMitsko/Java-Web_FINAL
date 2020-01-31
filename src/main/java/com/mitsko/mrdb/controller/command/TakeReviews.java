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
    private ReviewService reviewService;
    private UserService userService;

    private HashMap<String, Review> reviewMap;
    private HashMap<String, Integer> usersRatingMap;

    public TakeReviews() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        reviewService = serviceFactory.getReviewService();
        userService = serviceFactory.getUserService();

        reviewMap = new HashMap<>();
        usersRatingMap = new HashMap<>();
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = Constants.REVIEW;

        try {
            String movieName = req.getParameter("Review");
            ArrayList<Review> reviewList = reviewService.takeAllReview(movieName);

            takeLogins(reviewList);

            req.setAttribute("review", reviewMap);
            req.setAttribute("user", usersRatingMap);

            HttpSession session = req.getSession();
            session.setAttribute("movieName", movieName);
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
        return page;
    }

    private void takeLogins(ArrayList<Review> reviewList) {
        try {
            for (Review review : reviewList) {
                String login = userService.takeLogin(review.getUserID());
                reviewMap.put(login, review);
                int rating = userService.takeAverageRating(login);
                usersRatingMap.put(login, rating);
            }
        } catch (ServiceException ex) {

        }
    }
}
