package com.mitsko.mrdb.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        String path = req.getServletPath();

        Enumeration<String> names = req.getParameterNames();
        if (names.hasMoreElements() && (path.equals("/sign_in") || path.equals("/registration"))) {
            chain.doFilter(request, response);
        } else if (userID == null) {
            RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
