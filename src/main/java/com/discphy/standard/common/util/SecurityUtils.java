package com.discphy.standard.common.util;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Utility class for Spring Security related operations.
 */
@Component
public class SecurityUtils {

    /**
     * Get the current Authentication object.
     *
     * @return the current Authentication object
     * @throws AuthenticationCredentialsNotFoundException if authentication is null
     */
    private Authentication getCurrentAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AuthenticationCredentialsNotFoundException("Authentication object is null");
        }
        return authentication;
    }

    /**
     * Get the current authenticated user's principal.
     *
     * @return the principal object
     * @throws AuthenticationCredentialsNotFoundException if authentication or principal is null
     */
    public Object getUserPrincipal() {
        Authentication authentication = getCurrentAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            throw new AuthenticationCredentialsNotFoundException("Principal is null");
        }
        return principal;
    }

    /**
     * Get the current authenticated user's details.
     *
     * @return the UserDetails object of the current authenticated user
     * @throws UsernameNotFoundException if the user details cannot be found
     */
    public UserDetails getUserDetails() {
        Object principal = getUserPrincipal();

        return principal instanceof UserDetails ? (UserDetails) principal : null;
    }

    /**
     * Get the username of the current authenticated user.
     *
     * @return the username of the current authenticated user
     * @throws AuthenticationCredentialsNotFoundException if authentication or principal is null
     */
    public String getUsername() {
        UserDetails userDetails = getUserDetails();

        return Optional.of(userDetails).orElse(null).getUsername();
    }

    /**
     * Check if the current authenticated user has a specific role.
     *
     * @param role the role to check (e.g., "ROLE_ADMIN")
     * @return true if the current authenticated user has the specified role, false otherwise
     * @throws AuthenticationCredentialsNotFoundException if authentication or principal is null
     */
    public boolean hasRole(String role) {
        Authentication authentication = getCurrentAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
    }

    /**
     * Check if the current user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        return getCurrentAuthentication().isAuthenticated();
    }

    /**
     * Check if the current authenticated user has any of the specified roles.
     *
     * @param roles the roles to check
     * @return true if the current authenticated user has any of the specified roles, false otherwise
     * @throws AuthenticationCredentialsNotFoundException if authentication or principal is null
     */
    public boolean hasAnyRole(String... roles) {
        Authentication authentication = getCurrentAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> {
                    for (String role : roles) {
                        if (authority.getAuthority().equals(role)) {
                            return true;
                        }
                    }
                    return false;
                });
    }
}
