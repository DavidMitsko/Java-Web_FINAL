package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.PagesConstants;
import com.mitsko.mrdb.entity.Movie;
import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.service.MovieService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class TakeMovies implements Command {
    private int begin = 0;

    @Override
    public String execute(HttpServletRequest req) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getMovieService();

        try {
            recountOffset(req.getServletPath(), movieService.size());
            ArrayList<Movie> movieList = movieService.takeMovies(begin);

            req.setAttribute("movieList", movieList);
        } catch (ServiceException ex) {
            req.setAttribute("error", ex.getMessage());
            return PagesConstants.ERROR;
        }

        HttpSession session = req.getSession();
        Role role = (Role) session.getAttribute("role");
        if (role == Role.USER) {
            return PagesConstants.MAIN;
        } else {
            return PagesConstants.REMOVE;
        }
    }

    private void recountOffset(String req, int size) {
        if (req.equals("/next") && begin + 3 <= size) {
            begin += 3;
        }
        if (req.equals("/previous")) {
            begin -= 3;
            if(begin < 0) {
                begin = 0;
            }
        }
        if (req.equals("/take_movies") ||
                req.equals("/take_movies_for_remove")) {
            begin = 0;
        }
    }
}
