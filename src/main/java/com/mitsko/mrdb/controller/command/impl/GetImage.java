package com.mitsko.mrdb.controller.command.impl;

import com.mitsko.mrdb.controller.command.Command;
import com.mitsko.mrdb.controller.command.CommandException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GetImage implements Command {

    private final String path = "F:\\IntelliJ IDEA Ultimate\\Projects\\Java-Web_FINAL\\web\\images\\";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String fileName = req.getParameter("fileName");
        ServletContext servletContext = req.getServletContext();
        String filePath = path + fileName;
        String mime = servletContext.getMimeType(filePath);

        if (mime == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

        resp.setContentType(mime);
        File file = new File(filePath);
        resp.setContentLength((int) file.length());

        try {
            OutputStream outputStream = resp.getOutputStream();
            FileInputStream inputStream = new FileInputStream(file);

            byte[] buf = new byte[1024 * 1024 * 5];
            int count = 0;
            while ((count = inputStream.read(buf)) >= 0) {
                outputStream.write(buf, 0, count);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            throw new CommandException(ex);
        }

        return null;
    }
}
