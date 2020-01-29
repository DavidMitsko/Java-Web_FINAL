package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.service.MovieService;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class AddMovie implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getMovieService();

        String movieName = req.getParameter("movieName");

        movieService.addMovie(movieName);

        return Constants.ADMIN;
    }
}
