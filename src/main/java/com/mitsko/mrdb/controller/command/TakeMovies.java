package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.entity.Movie;
import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.service.MovieService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;

public class TakeMovies implements Command {
    private int begin = 0;

    @Override
    public String execute(HttpServletRequest req) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getMovieService();

        try {
            ArrayList<Movie> movieList = movieService.takeAllMovies();

            movieList = compileList(movieList, req);

            req.setAttribute("movieList", movieList);
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }

        HttpSession session = req.getSession();
        Role role = (Role) session.getAttribute("role");
        if (role == Role.USER) {
            return Constants.MAIN;
        } else {
            return Constants.REMOVE;
        }
    }

    private ArrayList<Movie> compileList(ArrayList<Movie> movieArrayList, HttpServletRequest req) {
        if (req.getServletPath().equals("/Next") && begin + 1 <= movieArrayList.size()) {
            begin += 3;
        }
        if (req.getServletPath().equals("/Previous")) {
            begin -= 3;
            if(begin < 0) {
                begin = 0;
            }
        }
        if (req.getServletPath().equals("/Take_Movies")) {
            begin = 0;
        }

        ArrayList<Movie> movieList = new ArrayList<>();
        for (int i = begin; i < begin + 3; i++) {
            if(i >= movieArrayList.size()) {
                break;
            }
            movieList.add(movieArrayList.get(i));
        }
        return movieList;
    }
}
