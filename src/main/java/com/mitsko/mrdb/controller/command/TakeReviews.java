package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.entity.Review;
import com.mitsko.mrdb.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

public class TakeReviews implements Command {
    private ReviewService reviewService;
    private UserService userService;
    private MovieService movieService;

    private HashMap<String, Review> reviewMap;
    private HashMap<String, Integer> usersRatingMap;

    public TakeReviews() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        reviewService = serviceFactory.getReviewService();
        userService = serviceFactory.getUserService();
        movieService = serviceFactory.getMovieService();

        reviewMap = new HashMap<>();
        usersRatingMap = new HashMap<>();
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = Constants.REVIEW;

        try {
            String movieName = req.getParameter("movieNameForReview");
            ArrayList<Review> reviewList = reviewService.takeAllReview(movieName);
            takeLogins(reviewList);

            String description = movieService.takeDescription(movieName);

            req.setAttribute("review", reviewMap);
            req.setAttribute("user", usersRatingMap);
            req.setAttribute("description", description);

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
