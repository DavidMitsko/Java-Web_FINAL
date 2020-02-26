package com.mitsko.mrdb.service.impl;

import com.mitsko.mrdb.dao.*;
import com.mitsko.mrdb.entity.Movie;
import com.mitsko.mrdb.service.*;
import com.mitsko.mrdb.service.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MovieServiceImpl implements MovieService {
    private final static Logger logger = LogManager.getLogger(MovieServiceImpl.class);

    private final static String path = "F:\\IntelliJ IDEA Ultimate\\Projects\\Java-Web_FINAL\\web\\images";

    private RatingDAO ratingDAO;
    private MovieDAO movieDAO;
    private RecountDAO recountDAO;
    private UserDAO userDAO;
    private Validator validator;

    public MovieServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ratingDAO = daoFactory.getSQLRatingDAO();
        movieDAO = daoFactory.getSQLMovieDAO();
        userDAO = daoFactory.getSQLUserDAO();
        recountDAO = daoFactory.getSQLRecountDAO();

        validator = new Validator();
    }

    @Override
    public void updateRating(String movieName) throws ServiceException {
        try {
            int movieID = movieDAO.takeID(movieName);
            float newRating = ratingDAO.takeAverageRatingOfMovie(movieID);
            if (newRating == -1) {
                throw new ServiceException();
            }

            movieDAO.updateRating(newRating, movieID);

            logger.debug(movieName + "s rating updated");
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public float takeAverageRating(String movieName) throws ServiceException {
        float rating;
        try {
            int id = movieDAO.takeID(movieName);
            rating = movieDAO.takeRatingOfMovie(id);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }

        if (rating == -1) {
            logger.error("There isn`t rating of this movie " + movieName);
            throw new ServiceException("There isn`t rating of this movie " + movieName);
        }

        logger.debug("Received rating of movie " + movieName);
        return rating;
    }

    @Override
    public ArrayList<Movie> takeMovies(int offset) throws ServiceException {
        ArrayList<Movie> movieList;
        try {
            movieList = movieDAO.takeMovies(offset);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }

        if (movieList.size() == 0) {
            logger.error("There isn`t any movies");
            throw new ServiceException("There isn`t any movies");
        }

        logger.debug("Received movies");
        return movieList;
    }

    @Override
    public int size() throws ServiceException {
        try {
            int size = movieDAO.size();

            if(size == -1) {
                logger.error("We have some problems with db");
                throw new ServiceException("We have some problems with db");
            }

            logger.debug("Received quantity of movie");
            return size;
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void updateMovie(String movieName, String newMovieName, String imageName, String description) throws ServiceException {
        try {
            int movieID = movieDAO.takeID(movieName);
            Movie movie = movieDAO.takeMovie(movieID);

            if(newMovieName != null && !newMovieName.equals("")) {
                movie.setName(newMovieName);
            }
            if(imageName != null && !imageName.equals("")) {
                String oldImage = movie.getImageName();
                if(oldImage != null && !oldImage.equals("")) {
                    deleteImage(oldImage);
                }
                movie.setImageName(imageName);
            }
            if(description != null && !description.equals("")) {
                movie.setDescription(description);
            }

            movieDAO.updateMovie(movie);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public void addMovie(String movieName, String imageName, String description) throws ServiceException {
        if(!validator.checkDescription(description)) {
            logger.error("Wrong parameter");
            throw new ServiceException("Wrong parameter");
        }

        Movie movie = new Movie(movieName, imageName, description);

        try {
            movieDAO.addMovie(movie);
            logger.debug("Added movie: " + movieName);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public void removeMovie(String movieName) throws ServiceException {
        try {
            int movieID = movieDAO.takeID(movieName);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService userService = serviceFactory.getUserService();

            ArrayList<Integer> usersID = userDAO.takeAllUsersID();
            for (int userID : usersID) {
                if (!recountDAO.find(userID, movieID)) {
                    continue;
                }
                int userRating = userService.reestablishUsersRating(userID, movieID);
                userDAO.updateRating(userID, userRating);
                logger.debug("Reestablish users ratings before removing");
            }

            RatingService ratingService = serviceFactory.getRatingService();
            ratingService.removeAllMoviesRatings(movieName);
            logger.debug("Removed all users ratings to this movie " + movieName);

            ReviewService reviewService = serviceFactory.getReviewService();
            reviewService.removeAllMoviesReview(movieName);
            logger.debug("Removed all users reviews to this movie " + movieName);

            String image = movieDAO.takeImageName(movieID);
            deleteImage(image);

            movieDAO.removeMovie(movieName);
            logger.debug("Removed movie");
        } catch (ServiceException ex) {
            logger.error(ex);
            throw ex;
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }

    private void deleteImage(String imageName) {
        String filePath = "F:\\IntelliJ IDEA Ultimate\\Projects\\Java-Web_FINAL\\web\\images\\" + imageName;

        File file = new File(filePath);
        file.delete();
    }

    @Override
    public String takeDescription(String movieName) throws ServiceException {
        String description;
        try {
            int movieID = movieDAO.takeID(movieName);

            description = movieDAO.takeDescription(movieID);

            logger.debug("Received description of " + movieName);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
        return description;
    }

    @Override
    public String takeNameByID(int movieID) throws ServiceException {
        try {
            String movieName = movieDAO.takeName(movieID);
            logger.debug("Received movies name");
            return movieName;
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException(ex);
        }
    }
}
