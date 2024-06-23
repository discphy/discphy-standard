package com.discphy.standard.common.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to handle common tasks related to HttpServletRequest and HttpSession.
 */
@Component
public class RequestUtils {

    /**
     * Get the current HttpServletRequest.
     *
     * @return the current HttpServletRequest
     * @throws IllegalStateException if no request attributes are found
     */
    public static HttpServletRequest getCurrentHttpRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new IllegalStateException("No request attributes found");
        }
        return attributes.getRequest();
    }

    /**
     * Get the current HttpSession, creating one if necessary.
     *
     * @return the current HttpSession
     */
    public static HttpSession getCurrentSession() {
        HttpServletRequest request = getCurrentHttpRequest();
        return request.getSession();
    }

    /**
     * Get the current HttpSession.
     *
     * @param create true to create a new session if none exists
     * @return the current HttpSession, or null if no session exists and create is false
     */
    public static HttpSession getCurrentSession(boolean create) {
        HttpServletRequest request = getCurrentHttpRequest();
        return request.getSession(create);
    }

    /**
     * Set an attribute in the current session.
     *
     * @param name  the name of the attribute
     * @param value the value of the attribute
     */
    public static void setSessionAttribute(String name, Object value) {
        HttpSession session = getCurrentSession();
        session.setAttribute(name, value);
    }

    /**
     * Get an attribute from the current session.
     *
     * @param name the name of the attribute
     * @return the value of the attribute, or null if not found or no session exists
     */
    public static Object getSessionAttribute(String name) {
        HttpSession session = getCurrentSession(false);
        return (session != null) ? session.getAttribute(name) : null;
    }

    /**
     * Invalidate the current session, if it exists.
     */
    public static void invalidateSession() {
        HttpSession session = getCurrentSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    /**
     * Get an attribute from the current request.
     *
     * @param name the name of the attribute
     * @return the value of the attribute, or null if not found
     */
    public static Object getRequestAttribute(String name) {
        HttpServletRequest request = getCurrentHttpRequest();
        return request.getAttribute(name);
    }

    /**
     * Set an attribute in the current request.
     *
     * @param name  the name of the attribute
     * @param value the value of the attribute
     */
    public static void setRequestAttribute(String name, Object value) {
        HttpServletRequest request = getCurrentHttpRequest();
        request.setAttribute(name, value);
    }

    /**
     * Get the full URL of the current request.
     *
     * @return the full URL
     */
    public static String getRequestURL() {
        HttpServletRequest request = getCurrentHttpRequest();
        return request.getRequestURL().toString();
    }

    /**
     * Get the URI of the current request.
     *
     * @return the URI
     */
    public static String getRequestURI() {
        HttpServletRequest request = getCurrentHttpRequest();
        return request.getRequestURI();
    }

    /**
     * Get the query string of the current request.
     *
     * @return the query string
     */
    public static String getQueryString() {
        HttpServletRequest request = getCurrentHttpRequest();
        return request.getQueryString();
    }

    /**
     * Get the scheme of the current request (e.g., http, https).
     *
     * @return the scheme
     */
    public static String getScheme() {
        HttpServletRequest request = getCurrentHttpRequest();
        return request.getScheme();
    }

    /**
     * Get the server name of the current request.
     *
     * @return the server name
     */
    public static String getServerName() {
        HttpServletRequest request = getCurrentHttpRequest();
        return request.getServerName();
    }

    /**
     * Get the server port of the current request.
     *
     * @return the server port
     */
    public static int getServerPort() {
        HttpServletRequest request = getCurrentHttpRequest();
        return request.getServerPort();
    }

    /**
     * Get the remote IP address of the client making the current request.
     *
     * @return the remote IP address
     */
    public static String getRemoteAddr() {
        HttpServletRequest request = getCurrentHttpRequest();
        return request.getRemoteAddr();
    }

    /**
     * Get the Host header value of the current request.
     *
     * @return the Host header value
     */
    public static String getHost() {
        HttpServletRequest request = getCurrentHttpRequest();
        return request.getHeader("Host");
    }

    /**
     * Get the subdomain from the Host header of the current request.
     *
     * @return the subdomain, or null if not present
     */
    public static String getSubDomain() {
        String host = getHost();
        if (host == null || host.isEmpty()) {
            return null;
        }
        String[] parts = host.split("\\.");
        if (parts.length > 2) {
            return parts[0];
        }
        return null;
    }

    /**
     * Get all query parameters from the current request as a Map.
     *
     * @return a Map of query parameters
     */
    public static Map<String, String> getQueryParameters() {
        HttpServletRequest request = getCurrentHttpRequest();
        Map<String, String> queryParameters = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            queryParameters.put(paramName, request.getParameter(paramName));
        }
        return queryParameters;
    }
}
