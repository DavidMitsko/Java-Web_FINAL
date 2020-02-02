package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.service.MovieService;
import com.mitsko.mrdb.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class AddMovie implements Command {
    private static final String imagePath = "F:\\IntelliJ IDEA Ultimate\\Projects\\Java-Web_FINAL\\web\\images";

    @Override
    public String execute(HttpServletRequest req) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getMovieService();

        String movieName = req.getParameter("movieName");
        String imageName = takeImage(req);
        String description = req.getParameter("movieDescription");

        movieService.addMovie(movieName, imageName, description);

        return Constants.ADMIN;
    }

    private String takeImage(HttpServletRequest req) throws IOException, ServletException {
        File dir = new File(imagePath);
        if(!dir.exists()) {
            dir.mkdir();
        }
        String fileName = null;
        for (Part part : req.getParts()) {
            fileName = part.getSubmittedFileName();//getFileName(part);
            part.write(imagePath + File.separator + fileName);
        }
        return fileName;
    }
}
