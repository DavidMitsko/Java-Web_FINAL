package com.mitsko.mrdb.controller.filter;

import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.resource.ResourceManager;

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
        if(role == Role.USER && (path.equals("add_movie") || path.equals("remove_movie") ||
                path.equals("change_status") || path.equals("take_users"))) {

            ResourceManager manager = ResourceManager.getInstance();

            request.setAttribute("error", manager.getString("error.lowAccess.message"));
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
