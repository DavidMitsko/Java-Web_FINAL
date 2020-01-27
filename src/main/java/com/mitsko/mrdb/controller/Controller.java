package com.mitsko.mrdb.controller;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private final CommandProvider commandProvider = new CommandProvider();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getServletPath();
        commandName = commandName.substring(1);

        Command executionCommand = commandProvider.getCommand(commandName);

        String page = executionCommand.execute(req);

        page = "pages/" + page + ".jsp";

        req.getRequestDispatcher(page).forward(req, resp);
    }
}
