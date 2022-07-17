package com.epam.task.web.project.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PreviousQueryFilter implements Filter {

    private static final String GET = "GET";
    private static final String COMMAND = "command";
    private static final String GET_RESOURCE = "getResource";
    private static final String CHANGE_LANGUAGE = "changeLanguage";
    private static final String PREVIOUS_QUERY = "previousQuery";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        if (GET.equals(request.getMethod())) {

            String command = request.getParameter(COMMAND);

            if (!CHANGE_LANGUAGE.equals(command) && !GET_RESOURCE.equals(command)) {
                String query = request.getQueryString();
                session.setAttribute(PREVIOUS_QUERY, query);
            }
        }

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {}
}
