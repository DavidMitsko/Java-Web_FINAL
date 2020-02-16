package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.*;
import com.mitsko.mrdb.entity.Review;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.entity.util.Status;
import com.mitsko.mrdb.service.ReviewService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ReviewServiceImpl implements ReviewService {
    private final static Logger logger = LogManager.getLogger(ReviewServiceImpl.class);

    private ReviewDAO reviewDAO;
    private UserDAO userDAO;
    private MovieDAO movieDAO;

    private Validator validator;

    public ReviewServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        reviewDAO = daoFactory.getSQLReviewDAO();
        userDAO = daoFactory.getSQLUserDAO();
        movieDAO = daoFactory.getSQLMovieDAO();

        validator = new Validator();
    }

    @Override
    public void addNewReview(int userID, String movieName, String review) throws ServiceException {
        if(!validator.checkReview(review)) {
            logger.error("Wrong parameter");
            throw new ServiceException("Wrong parameter");
        }
        try {
            int movieID = movieDAO.takeID(movieName);

            Review newReview = new Review(userID, movieID, review);
            reviewDAO.addReview(newReview);

            logger.debug("Added new review to " + movieName);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public void removeReview(int userID, int reviewID) throws ServiceException {
        try {
            Review review = reviewDAO.takeByID(reviewID);

            if (review.getUserID() == userID) {
                reviewDAO.removeReview(reviewID);
                logger.debug("Removed review");
            } else {
                logger.error("Low access");
                throw new ServiceException("Low access");
            }
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public ArrayList<Review> takeAllMoviesReview(String movieName) throws ServiceException {
        ArrayList<Review> reviewList;
        try {
            int movieID = movieDAO.takeID(movieName);
            reviewList = reviewDAO.takeAllMoviesReviews(movieID);

            for (int i = 0; i < reviewList.size(); i++) {
                Review review = reviewList.get(i);
                int userID = review.getUserID();
                Status status = userDAO.takeStatus(userID);
                if (status == Status.BAN) {
                    reviewList.remove(i);
                }
            }

            logger.debug("Received all movies reviews");
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
        return reviewList;
    }

    @Override
    public void removeAllMoviesReview(String movieName) throws ServiceException {
        try {
            int movieID = movieDAO.takeID(movieName);
            reviewDAO.removeAllReviews(movieID);
            logger.debug("Removed all " + movieName + "`s review");
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public ArrayList<Review> takeAllUsersReview(int userID) throws ServiceException {
        try {
            ArrayList<Review> usersReview = reviewDAO.takeAllUsersReviews(userID);
            logger.debug("Received all users review");
            return usersReview;
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }
}
