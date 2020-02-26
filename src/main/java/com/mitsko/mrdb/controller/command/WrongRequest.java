package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.PagesConstants;
import com.mitsko.mrdb.resource.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WrongRequest implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ResourceManager manager = ResourceManager.getInstance();

        req.setAttribute("error", manager.getString("error.wrongRequest.message"));
        return PagesConstants.ERROR;
    }
}
