package com.mitsko.mrdb.controller.util;

import com.mitsko.mrdb.entity.util.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        Role role = (Role)session.getAttribute("role");

        String path = req.getServletPath();
        if(role == Role.USER && (path.equals("/Add_Movie") || path.equals("/Remove_Movie") ||
                path.equals("/Change_Status") || path.equals("admin.jsp"))) {
            RequestDispatcher requestDispatcher = request.getServletContext()
                    .getRequestDispatcher("/pages/lowAccess.html");
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
