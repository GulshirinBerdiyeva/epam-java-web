package com.epam.task.web.project.filter;

import com.epam.task.web.project.cookie.CookieManager;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CookieFlagRemoverFilter implements Filter {

    private static final String TRUE = "true";

    private CookieManager cookieManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        cookieManager = CookieManager.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            Arrays.stream(cookies)
                    .filter(cookie -> TRUE.equals(cookie.getValue()))
                    .forEach(cookie -> cookieManager.removeCookie(response,cookie));
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        cookieManager = null;
    }

}
