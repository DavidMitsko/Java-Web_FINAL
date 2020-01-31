package com.mitsko.mrdb.controller;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.CommandProvider;
import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Status;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Controller extends HttpServlet {
    private final CommandProvider commandProvider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String commandName = req.getServletPath();
        commandName = commandName.substring(1);

        /*HttpSession session = req.getSession();
        Status userStatus = (Status) session.getAttribute("status");
        Integer id = (Integer)session.getAttribute("userID");
        if(id == null && !(commandName.equals("Sign_In") || commandName.equals("Registration"))) {
            return;
        }
        if(userStatus != null) {
            if (userStatus == Status.BAN) {
                resp.sendRedirect(Constants.BAN_HTML);
                return;
            }
        }*/

        Command executionCommand = commandProvider.getCommand(commandName);

        String page = executionCommand.execute(req);

        boolean flag = false;
        if (page.equals(Constants.MAIN)) {
            flag = true;
            commandName = Constants.TAKE_MOVIE;
        } else if (page.equals(Constants.ADMIN)) {
            flag = true;
            commandName = Constants.TAKE_USERS;
        }
        if (flag) {
            executionCommand = commandProvider.getCommand(commandName);
            executionCommand.execute(req);
        }

        if(!page.equals(Constants.INDEX)) {
            page = "pages/" + page + ".jsp";
        }
        else {
            page = page + ".jsp";
        }

        req.getRequestDispatcher(page).forward(req, resp);
    }
}
