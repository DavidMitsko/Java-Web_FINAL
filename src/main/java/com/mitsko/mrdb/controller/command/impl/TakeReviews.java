package com.mitsko.mrdb.controller.command.impl;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.CommandException;
import com.mitsko.mrdb.controller.command.util.PagesConstants;
import com.mitsko.mrdb.entity.Review;
import com.mitsko.mrdb.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String page = PagesConstants.REVIEW;
        reviewMap = new HashMap<>();
        usersRatingMap = new HashMap<>();
        try {
            String movieName = req.getParameter("movieNameForReview");
            ArrayList<Review> reviewList = reviewService.takeAllMoviesReview(movieName);
            takeLogins(reviewList);

            String description = movieService.takeDescription(movieName);

            req.setAttribute("review", reviewMap);
            req.setAttribute("user", usersRatingMap);
            req.setAttribute("description", description);

            HttpSession session = req.getSession();
            session.setAttribute("movieName", movieName);
        } catch (ServiceException ex) {
//            req.setAttribute("error", ex.getMessage());
//            return PagesConstants.ERROR;
            throw new CommandException(ex);
        }
        return page;
    }

    private void takeLogins(ArrayList<Review> reviewList) throws ServiceException {
        reviewMap = new HashMap<>();
        usersRatingMap = new HashMap<>();

        for (Review review : reviewList) {
            String login = userService.takeLogin(review.getUserID());
            int rating = userService.takeAverageRating(login);
            while (reviewMap.containsKey(login)) {
                login = login + " ";
            }
            reviewMap.put(login, review);
            usersRatingMap.put(login, rating);

        }
    }
}
