package com.epam.task.web.project.filter;

import com.epam.task.web.project.entity.Role;
import com.epam.task.web.project.entity.User;
import com.google.common.collect.ImmutableMap;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthorizationFilter implements Filter {

    private static final String USER = "user";
    private static final String COMMAND = "command";

    private ImmutableMap<String, List<Role>> userCommands;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userCommands = ImmutableMap.<String, List<Role>>builder()
                .put("changeLanguage", Arrays.asList(Role.ADMIN, Role.CLIENT, Role.UNKNOWN))
                .put("signUpPage", Arrays.asList(Role.UNKNOWN))
                .put("signIn", Arrays.asList(Role.UNKNOWN))
                .put("login", Arrays.asList(Role.UNKNOWN))
                .put("logout", Arrays.asList(Role.ADMIN, Role.CLIENT, Role.UNKNOWN))
                .put("main", Arrays.asList(Role.ADMIN, Role.CLIENT))
                .put("getMusic", Arrays.asList(Role.ADMIN, Role.CLIENT))
                .put("getResource", Arrays.asList(Role.ADMIN, Role.CLIENT))
                .put("searchMusic", Arrays.asList(Role.ADMIN, Role.CLIENT))
                .put("selectMusic", Arrays.asList(Role.ADMIN, Role.CLIENT))
                .put("purchasePage", Arrays.asList(Role.ADMIN, Role.CLIENT))
                .put("buy", Arrays.asList(Role.CLIENT))
                .put("confirmPurchase", Arrays.asList(Role.CLIENT))
                .put("cancelPurchase", Arrays.asList(Role.CLIENT))
                .put("editMusic", Arrays.asList(Role.ADMIN))
                .put("editPrice", Arrays.asList(Role.ADMIN))
                .put("deleteMusic", Arrays.asList(Role.ADMIN))
                .put("cancelDelete", Arrays.asList(Role.ADMIN))
                .put("confirmDelete", Arrays.asList(Role.ADMIN))
                .put("playlist", Arrays.asList(Role.CLIENT))
                .put("albums", Arrays.asList(Role.ADMIN, Role.CLIENT))
                .put("createAlbumPage", Arrays.asList(Role.ADMIN))
                .put("albumMusics", Arrays.asList(Role.ADMIN, Role.CLIENT))
                .put("addAlbum", Arrays.asList(Role.ADMIN))
                .put("addMusic", Arrays.asList(Role.ADMIN))
                .put("addMusicPage", Arrays.asList(Role.ADMIN))
                .put("comments", Arrays.asList(Role.ADMIN, Role.CLIENT))
                .put("createComment", Arrays.asList(Role.ADMIN, Role.CLIENT))
                .put("profilePage", Arrays.asList(Role.CLIENT))
                .put("fillBalance", Arrays.asList(Role.CLIENT))
                .put("clients", Arrays.asList(Role.ADMIN))
                .put("applyDiscount", Arrays.asList(Role.ADMIN))
                .build();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getParameter(COMMAND);
        boolean exist = userCommands.containsKey(command);

        if (exist) {
            User user = (User) request.getSession().getAttribute(USER);
            Role userRole = user != null ? user.getRole() : Role.UNKNOWN;

            List<Role> roles = userCommands.get(command);
            boolean validCommand = roles.contains(userRole);

            if (!validCommand) {
                response.sendRedirect(request.getRequestURI());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        userCommands.clear();
    }

}