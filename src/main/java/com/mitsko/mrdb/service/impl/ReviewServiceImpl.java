package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.DAOFactory;
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

    public ReviewServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        reviewDAO = daoFactory.getSQLReviewDAO();
        userDAO = daoFactory.getSQLUserDAO();
    }

    @Override
    public void addNewReview(String userLogin, String movieName, String review) throws ServiceException {
        if (userLogin.equals("") || movieName.equals("") || review.equals("")) {
            throw new ServiceException("Wrong parameter");
        }

        Review newReview = new Review(userLogin, movieName, review);
        reviewDAO.addReview(newReview);
    }

    @Override
    public void removeReview(User user, String userLogin, String movieName) throws ServiceException {
        if (user == null || movieName.equals("") || userLogin.equals("")) {
            throw new ServiceException("Wrong parameter");
        }

        if (user.getLogin().equals(userLogin) || user.getRole() == Role.ADMIN) {
            reviewDAO.removeReview(userLogin, movieName);
        }
    }

    @Override
    public ArrayList<Review> takeAllReview(String movieName) throws ServiceException {
        ArrayList<Review> reviewList = reviewDAO.takeAllMoviesReviews(movieName);

        /*if(reviewList.isEmpty()) {
            throw new ServiceException();
        }*/
        for (int i = 0; i < reviewList.size(); i++) {//(Review review : reviewList) {
            Review review = reviewList.get(i);
            String login = review.getUserLogin();
            Status status = userDAO.takeStatus(login);
            if(status == Status.BAN) {
                reviewList.remove(i);
            }
        }

        return reviewList;
    }
}
