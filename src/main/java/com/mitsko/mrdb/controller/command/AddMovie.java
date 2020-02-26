package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.PagesConstants;
import com.mitsko.mrdb.controller.command.util.RequestsConstants;
import com.mitsko.mrdb.service.MovieService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class AddMovie implements Command {
    private final boolean command;
    private static final String imagePath = "F:\\IntelliJ IDEA Ultimate\\Projects\\Java-Web_FINAL\\web\\images";

    public AddMovie(boolean command) {
        this.command = command;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getMovieService();

        String movieName = req.getParameter("movieName");
        String imageName = takeImage(req);
        String description = req.getParameter("movieDescription");

        try {
            if(command) {
                movieService.addMovie(movieName, imageName, description);
            } else {
                String oldMovieName = (String)req.getSession().getAttribute("movieName");
                movieService.updateMovie(oldMovieName, movieName, imageName, description);
            }
        } catch (ServiceException ex) {
            req.setAttribute("error", ex.getMessage());
            return PagesConstants.ERROR;
        }

        return RequestsConstants.TAKE_USERS;
    }

    private String takeImage(HttpServletRequest req) {
        File dir = new File(imagePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String fileName = null;
        try {
            for (Part part : req.getParts()) {
                fileName = part.getSubmittedFileName();
                if(fileName == null) {
                    continue;
                }
                part.write(imagePath + File.separator + fileName);
            }
        } catch (IOException | ServletException ex) {
            fileName = null;
        }
        return fileName;
    }
}
