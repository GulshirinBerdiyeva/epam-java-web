package com.epam.task.web.project.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocalizationFilter implements Filter {

    private static final String LOCALE = "locale";
    private static final String EN = "en";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String locale = (String) session.getAttribute(LOCALE);

        if (locale == null) {
            locale = EN;
        }

        session.setAttribute(LOCALE, locale);

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {}

}
