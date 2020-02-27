package com.mitsko.mrdb.controller.command.impl;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.resource.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLocale implements Command {
    public static final Locale EN = new Locale("en", "US");
    public static final Locale RU = new Locale("ru", "RU");

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ResourceManager resourceManager = ResourceManager.getInstance();

        HttpSession session = req.getSession();
        Locale currentLocale = resourceManager.getCurrentLocale();
        if(currentLocale.equals(EN)) {
            resourceManager.changeResource(RU);
            session.setAttribute("locale", RU);
        } else {
            resourceManager.changeResource(EN);
            session.setAttribute("locale", EN);
        }

        return compileRequest(req.getParameter("path"), req.getParameter("query"));
    }

    private String compileRequest(String path, String query) {
        path = path.replaceAll("pages/", "");
        path = path.replaceAll(".jsp","");
        return path + "?" + query;
    }
}
