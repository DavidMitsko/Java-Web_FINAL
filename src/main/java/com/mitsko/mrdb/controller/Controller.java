package com.mitsko.mrdb.controller;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.CommandProvider;
import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.entity.util.Role;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = processRequest(req, resp);
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = processRequest(req, resp);
        resp.sendRedirect(request);
    }

    private String processRequest(HttpServletRequest req, HttpServletResponse resp) {
        String commandName = req.getServletPath();
        commandName = commandName.substring(1);

        if(commandName.equals("next") || commandName.equals("previous")) {
            if(req.getSession().getAttribute("role") == Role.USER) {
                commandName = Constants.TAKE_MOVIE;
            } else {
                commandName = Constants.TAKE_MOVIES_FOR_REMOVE;
            }
        }
        Command executionCommand = commandProvider.getCommand(commandName);

        return executionCommand.execute(req);
    }
}
