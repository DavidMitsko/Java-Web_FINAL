package com.mitsko.mrdb.controller;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.CommandException;
import com.mitsko.mrdb.controller.command.CommandProvider;
import com.mitsko.mrdb.controller.command.util.PagesConstants;
import com.mitsko.mrdb.controller.command.util.RequestsConstants;
import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.service.UserException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@MultipartConfig(fileSizeThreshold = 1024 *  1024,
        maxFileSize = 1024 *  1024 *  5,
        maxRequestSize = 1024 *  1024 *  5 *  5)
public class Controller extends HttpServlet {
    private final CommandProvider commandProvider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String page = processRequest(req, resp);
            req.getRequestDispatcher(page).forward(req, resp);
        } catch (CommandException ex) {
            handlingOfError(ex, req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String request = processRequest(req, resp);
            resp.sendRedirect(request);
        } catch (CommandException ex) {
            handlingOfError(ex, req, resp);
        }
    }

    private String processRequest(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String commandName = req.getServletPath();
        commandName = commandName.substring(1);

        if(commandName.equals("next") || commandName.equals("previous") || commandName.equals("change_page")) {
            if(req.getSession().getAttribute("role") == Role.USER) {
                commandName = RequestsConstants.TAKE_MOVIE;
            } else {
                commandName = RequestsConstants.EDITING_MOVIES;
            }
        }
        Command executionCommand = commandProvider.getCommand(commandName);

        return executionCommand.execute(req, resp);
    }

    private void handlingOfError(CommandException ex, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page;
        if(ex.getCause().getClass() == UserException.class) {
            page = req.getServletPath();
            page = page.substring(1);

            page = "pages/" + page + ".jsp";

            req.setAttribute("message", ex.getCause().getMessage());
        } else {
            req.setAttribute("error", ex.getMessage());
            page = PagesConstants.ERROR;
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
