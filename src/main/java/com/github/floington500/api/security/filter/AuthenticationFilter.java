package com.github.floington500.api.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Processing filter to manage the authentication of POST requests.
 */
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String API_KEY_HEADER = "Authorization";

    @Value("${spring.api.key}")
    private String API_KEY;

    /**
     * Authenticates users based off of the bearer token in the authorization header.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader(API_KEY_HEADER);

        if (authorization != null && authorization.equals("Bearer " + API_KEY)) {
            Authentication authentication = createAuthenticationToken(API_KEY);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Creates an authentication token given an API key.
     * @return an authentication token in the form of
     * {@link UsernamePasswordAuthenticationToken} for simple
     * configuration.
     */
    private Authentication createAuthenticationToken(String apiKey) {
        return new UsernamePasswordAuthenticationToken(
                apiKey, null, AuthorityUtils.NO_AUTHORITIES
        );
    }
}
