package com.epam.task.web.project.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        XssRequestWrapper xssRequestWrapper = new XssRequestWrapper(request);

        filterChain.doFilter(xssRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {}

}
