package com.mitsko.mrdb.service;

import com.mitsko.mrdb.service.impl.MovieServiceImpl;
import com.mitsko.mrdb.service.impl.RatingServiceImpl;
import com.mitsko.mrdb.service.impl.UserServiceImpl;

public class ServiceFactory {
    private final static ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final MovieService movieService = new MovieServiceImpl();
    private final RatingService ratingService = new RatingServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public RatingService getRatingService() {
        return ratingService;
    }
}
