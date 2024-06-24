package com.discphy.standard.common.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Utility class to handle common tasks related to HttpServletResponse.
 */
@Component
public class ResponseUtils {

    /**
     * Get the current HttpServletResponse.
     *
     * @return the current HttpServletResponse
     * @throws IllegalStateException if no request attributes are found
     */
    public static HttpServletResponse getCurrentHttpResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new IllegalStateException("No request attributes found");
        }
        return attributes.getResponse();
    }

    /**
     * Add a cookie to the response.
     *
     * @param name     the name of the cookie
     * @param value    the value of the cookie
     * @param maxAge   the maximum age of the cookie in seconds
     */
    public static void addCookie(String name, String value, int maxAge) {
        HttpServletResponse response = getCurrentHttpResponse();
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * Remove a cookie from the response.
     *
     * @param name     the name of the cookie to remove
     */
    public static void removeCookie(String name) {
        HttpServletResponse response = getCurrentHttpResponse();
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}

