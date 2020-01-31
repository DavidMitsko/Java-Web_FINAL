package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class SignOut implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        /*session.removeAttribute("userID");
        session.removeAttribute("role");
        session.removeAttribute("status");*/
        Enumeration<String> names = session.getAttributeNames();

        while(names.hasMoreElements()) {
            session.removeAttribute(names.nextElement());
        }

        return Constants.INDEX;
    }
}
