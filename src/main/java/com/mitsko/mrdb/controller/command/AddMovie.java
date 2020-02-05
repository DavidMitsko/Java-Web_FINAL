package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.service.MovieService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class AddMovie implements Command {
    private static final String imagePath = "F:\\IntelliJ IDEA Ultimate\\Projects\\Java-Web_FINAL\\web\\images";

    @Override
    public String execute(HttpServletRequest req) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getMovieService();

        String movieName = req.getParameter("movieName");
        String imageName = takeImage(req);
        String description = req.getParameter("movieDescription");

        try {
            movieService.addMovie(movieName, imageName, description);
        } catch (ServiceException ex) {
            String stackTrace = Arrays.toString(ex.getStackTrace());
            req.setAttribute("error", stackTrace);
            return Constants.ERROR;
        }

        return Constants.ADMIN;
    }

    private String takeImage(HttpServletRequest req) {
        File dir = new File(imagePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String fileName = null;
        try {
            for (Part part : req.getParts()) {
                fileName = part.getSubmittedFileName();//getFileName(part);
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
