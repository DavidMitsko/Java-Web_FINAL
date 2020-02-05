package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;
import com.mitsko.mrdb.resource.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLocale implements Command {
    public static final Locale EN = new Locale("en", "US");
    public static final Locale RU = new Locale("ru", "RU");

    @Override
    public String execute(HttpServletRequest req) {
        ResourceManager resourceManager = ResourceManager.getInstance();

        /*String page = req.getParameter("page");
        page = page.substring(22);*/

        HttpSession session = req.getSession();
        Locale currentLocale = resourceManager.getCurrentLocale();
        if(currentLocale.equals(EN)) {
            resourceManager.changeResource(RU);
            session.setAttribute("locale", RU);
        } else {
            resourceManager.changeResource(EN);
            session.setAttribute("locale", EN);
        }
        return Constants.MAKE_REDIRECT;
    }
}
