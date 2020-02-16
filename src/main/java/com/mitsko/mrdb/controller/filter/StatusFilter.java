package com.mitsko.mrdb.controller.filter;

import com.mitsko.mrdb.entity.util.Status;
import com.mitsko.mrdb.resource.ResourceManager;

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

        if(status == Status.BAN) {
            session.removeAttribute("userID");
            session.removeAttribute("role");
            session.removeAttribute("status");

            ResourceManager manager = ResourceManager.getInstance();

            request.setAttribute("error", manager.getString("error.ban.message"));
            RequestDispatcher requestDispatcher = request.getServletContext()
                    .getRequestDispatcher("/pages/error/error.jsp");
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
