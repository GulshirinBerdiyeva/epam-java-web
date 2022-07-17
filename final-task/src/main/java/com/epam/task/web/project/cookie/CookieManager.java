package com.epam.task.web.project.cookie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CookieManager {

    private static final Logger LOGGER = LogManager.getLogger(CookieManager.class);

    private static final AtomicReference<CookieManager> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private CookieManager() {}

    public static CookieManager getInstance() {
        if (!IS_INSTANCE_CREATED.get()) {

            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    CookieManager cookieManager = new CookieManager();

                    INSTANCE.set(cookieManager);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created CookieManager instance");
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }

    public Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {

                if (name.equals(cookie.getName())) {
                    return Optional.of(cookie);
                }
            }
        }

        return Optional.empty();
    }

    public void setCookie(HttpServletResponse response, String name, String value) {
        if (name != null && value != null) {
            Cookie cookie = new Cookie(name, value);
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
        }
    }

    public void removeCookie(HttpServletResponse response, Cookie cookie) {
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
