package com.epam.task.web.project.filter;

import com.epam.task.web.project.entity.Role;
import com.epam.task.web.project.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private static final String COMMAND = "command";
    private static final String USER = "user";
    private static final String ADMIN = "ADMIN";
    private static final String CLIENT = "CLIENT";
    private static final String MAIN_PAGE_COMMAND = "?command=mainPage";
    private static final String PROFILE_PAGE_COMMAND = "?command=profilePage";
    private static final String LOGIN_PAGE_COMMAND = "?command=loginPage";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getParameter(COMMAND);

        if (command == null || command.trim().isEmpty()) {
            User user = (User) request.getSession().getAttribute(USER);

            String role = user != null ? user.getRole().name() : Role.UNKNOWN.name();

            switch (role) {
                case ADMIN:
                    command = MAIN_PAGE_COMMAND;
                    break;
                case CLIENT:
                    command = PROFILE_PAGE_COMMAND;
                    break;
                default:
                    command = LOGIN_PAGE_COMMAND;
            }

            response.sendRedirect(request.getRequestURI() + command);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}

}
