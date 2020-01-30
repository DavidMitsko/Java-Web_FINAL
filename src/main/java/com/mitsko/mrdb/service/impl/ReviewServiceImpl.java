package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.DAOFactory;
import com.mitsko.mrdb.dao.MovieDAO;
import com.mitsko.mrdb.dao.ReviewDAO;
import com.mitsko.mrdb.dao.UserDAO;
import com.mitsko.mrdb.entity.Review;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.entity.util.Status;
import com.mitsko.mrdb.service.ReviewService;
import com.mitsko.mrdb.service.ServiceException;

import java.util.ArrayList;

public class ReviewServiceImpl implements ReviewService {
    private ReviewDAO reviewDAO;
    private UserDAO userDAO;
    private MovieDAO movieDAO;

    public ReviewServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        reviewDAO = daoFactory.getSQLReviewDAO();
        userDAO = daoFactory.getSQLUserDAO();
        movieDAO = daoFactory.getSQLMovieDAO();
    }

    @Override
    public void addNewReview(int userID, String movieName, String review) throws ServiceException {
        /*if (userLogin.equals("") || movieName.equals("") || review.equals("")) {
            throw new ServiceException("Wrong parameter");
        }*/
        int movieID = movieDAO.takeID(movieName);

        Review newReview = new Review(userID, movieID, review);
        reviewDAO.addReview(newReview);
    }

    @Override
    public void removeReview(User user, int userID, String movieName) throws ServiceException {
        /*if (user == null || movieName.equals("") || userLogin.equals("")) {
            throw new ServiceException("Wrong parameter");
        }*/
        int movieID = movieDAO.takeID(movieName);

        if (user.getID() == userID || user.getRole() == Role.ADMIN) {
            reviewDAO.removeReview(userID, movieID);
        }
    }

    @Override
    public ArrayList<Review> takeAllReview(String movieName) throws ServiceException {
        int movieID = movieDAO.takeID(movieName);
        ArrayList<Review> reviewList = reviewDAO.takeAllMoviesReviews(movieID);

        /*if(reviewList.isEmpty()) {
            throw new ServiceException();
        }*/
        for (int i = 0; i < reviewList.size(); i++) {//(Review review : reviewList) {
            Review review = reviewList.get(i);
            int userID = review.getUserID();
            Status status = userDAO.takeStatus(userID);
            if (status == Status.BAN) {
                reviewList.remove(i);
            }
        }

        return reviewList;
    }
}
