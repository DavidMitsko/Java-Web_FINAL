package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.PagesConstants;
import com.mitsko.mrdb.entity.Review;
import com.mitsko.mrdb.service.MovieService;
import com.mitsko.mrdb.service.ReviewService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

public class TakeHistory implements Command {
    private ReviewService reviewService;
    private MovieService movieService;

    public TakeHistory() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        reviewService = serviceFactory.getReviewService();
        movieService = serviceFactory.getMovieService();
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int userID = (int)session.getAttribute("userID");

        try {
            ArrayList<Review> reviewList = reviewService.takeAllUsersReview(userID);
            HashMap<String, Review> usersReview = compileUsersReview(reviewList);

            req.setAttribute("reviewMap", usersReview);
        } catch (ServiceException ex) {
            req.setAttribute("error", ex.getMessage());
            return PagesConstants.ERROR;
        }
        return PagesConstants.HISTORY;
    }

    private HashMap<String, Review> compileUsersReview(ArrayList<Review> reviewList) throws ServiceException {
        HashMap<String, Review> usersReview = new HashMap<>();
        for(Review review : reviewList) {
            String movieName = movieService.takeNameByID(review.getMovieID());
            while(usersReview.containsKey(movieName)) {
                movieName = movieName + " ";
            }
            usersReview.put(movieName, review);
        }
        return usersReview;
    }
}
