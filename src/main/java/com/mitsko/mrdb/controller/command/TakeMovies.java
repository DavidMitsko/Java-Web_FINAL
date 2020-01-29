package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.entity.Movie;
import com.mitsko.mrdb.service.MovieService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class TakeMovies implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getMovieService();

        try {
            ArrayList<Movie> movieList = movieService.takeAllMovies();

            req.setAttribute("movieList", movieList);
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }

        return Constants.MAIN;
    }
}
