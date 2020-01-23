package com.mitsko.mrdb.dao;

import com.mitsko.mrdb.dao.impl.*;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();

    private final UserDAO SQLUserDAO = new SQLUserDAOImpl();
    private final AdminDAO SQLAdminDAO = new SQLAdminDAOImpl();
    private final MovieDAO SQLMovieDAO = new SQLMovieDAOImpl();
    private final RatingDAO SQLRatingDAO = new SQLRatingDAOImpl();
    private final ReviewDAO SQLReviewDAO = new SQLReviewDAOImpl();

    private DAOFactory(){}

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getSQLUserDAO() {
        return SQLUserDAO;
    }

    public AdminDAO getSQLAdminDAO() {
        return SQLAdminDAO;
    }

    public MovieDAO getSQLMovieDAO() {
        return SQLMovieDAO;
    }

    public RatingDAO getSQLRatingDAO() {
        return SQLRatingDAO;
    }

    public ReviewDAO getSQLReviewDAO() {
        return SQLReviewDAO;
    }
}
