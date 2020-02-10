package com.mitsko.mrdb.controller.filter;

import com.mitsko.mrdb.entity.util.Status;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StatusFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        Status status = (Status)session.getAttribute("status");
        String path = req.getServletPath();

        if(status == Status.BAN && !path.equals("/Sign_Out")) {
            session.removeAttribute("userID");
            session.removeAttribute("role");
            session.removeAttribute("status");

            RequestDispatcher requestDispatcher = request.getServletContext()
                    .getRequestDispatcher("/pages/error/ban.html");
            requestDispatcher.forward(req, resp);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
