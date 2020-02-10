package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;

import javax.servlet.http.HttpServletRequest;

public class AddMoviePage implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return Constants.ADD_MOVIE;
    }
}
