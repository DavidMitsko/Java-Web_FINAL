package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.PagesConstants;
import com.mitsko.mrdb.entity.Movie;
import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.service.MovieService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class TakeMovies implements Command {
    private int begin = 0;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getMovieService();

        try {
            int size = movieService.size();
            recountOffset(size, req);
            ArrayList<Movie> movieList = movieService.takeMovies(begin);

            req.setAttribute("movieList", movieList);
            req.setAttribute("size", countPages(size));
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

    private int countPages(int size) {
        if (size % 3 != 0) {
            return size / 3 + 1;
        } else {
            return size / 3;
        }
    }

    private void recountOffset(int size, HttpServletRequest req) {
        String request = req.getServletPath();

        if (request.equals("/change_page")) {
            String index = req.getParameter("page");
            begin = Integer.parseInt(index) * 3 - 3;
        }
        if (request.equals("/next") && begin + 3 < size) {
            begin += 3;
        }
        if (request.equals("/previous")) {
            begin -= 3;
            if (begin < 0) {
                begin = 0;
            }
        }
        if (request.equals("/take_movies") ||
                request.equals("/take_movies_for_remove")) {
            begin = 0;
        }
    }
}
