package com.epam.task.web.project.filter;

import org.apache.commons.lang.StringEscapeUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class XssRequestWrapper extends HttpServletRequestWrapper {

    private static final String AMPERSAND = "amp;";

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return escapeXss(value);
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return escapeXss(value);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (values == null) {
            return null;
        }

        List<String> encodedValues = new ArrayList<>();

        Stream<String> stream = Arrays.stream(values);

        stream.forEach(value -> encodedValues.add(escapeXss(value)));

        return encodedValues.toArray(new String[0]);
    }

    @Override
    public String getQueryString() {
        String queryString = super.getQueryString();
        return escapeXss(queryString);
    }

    @Override
    public Cookie[] getCookies() {
        Cookie[] cookies = super.getCookies();

        if (cookies == null) {
            return null;
        }

        List<Cookie> encodedCookies = new ArrayList<>();

        Stream<Cookie> stream = Arrays.stream(cookies);

        stream.forEach(cookie -> {
            String name = escapeXss(cookie.getName());
            String value = escapeXss(cookie.getValue());

            if (name != null && value != null) {
                encodedCookies.add(new Cookie(name, value));
            }
        });

        return encodedCookies.toArray(new Cookie[0]);
    }


    private String escapeXss(String value) {
        if (value == null) {
            return null;
        }

        value = StringEscapeUtils.escapeJavaScript(value);
        value = StringEscapeUtils.escapeHtml(value);
        value = StringEscapeUtils.escapeSql(value);
        value = StringEscapeUtils.unescapeJavaScript(value);

        return value.replaceAll(AMPERSAND, "");
    }

}
