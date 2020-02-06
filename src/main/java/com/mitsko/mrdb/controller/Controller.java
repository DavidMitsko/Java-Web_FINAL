package com.mitsko.mrdb.controller;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.CommandProvider;
import com.mitsko.mrdb.controller.command.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 *  1024,
        maxFileSize = 1024 *  1024 *  5,
        maxRequestSize = 1024 *  1024 *  5 *  5)
public class Controller extends HttpServlet {
    private final CommandProvider commandProvider = new CommandProvider();

    private String previousRequest;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = processRequest(req, resp);
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = processRequest(req, resp);
        resp.sendRedirect(page);
    }

    private String processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String commandName = req.getServletPath();
        commandName = commandName.substring(1);

        if(commandName.equals("Next") || commandName.equals("Previous")) {
            commandName = Constants.TAKE_MOVIE;
        }
        Command executionCommand = commandProvider.getCommand(commandName);

        String page = executionCommand.execute(req);

//        if(page.equals(Constants.MAKE_REDIRECT)) {
//            executionCommand = commandProvider.getCommand(previousRequest);
//            page = executionCommand.execute(req);
//        } else {
//            previousRequest = commandName;
//        }

        return page;
        //req.getRequestDispatcher(page).forward(req, resp);
    }
}
